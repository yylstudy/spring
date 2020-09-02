package com.yyl.config.spring;

import com.yyl.bean.BaseResponse;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.ResourceRegionHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.http.converter.feed.AtomFeedHttpMessageConverter;
import org.springframework.http.converter.feed.RssChannelHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.smile.MappingJackson2SmileHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 自定义spring mvc返回json格式，这个可以和exceptionHandler组合构成特定的json返回格式
 * @Author: yyl
 * @Date: 2019/1/28 9:39
 */
public class MyHandlerMethodReturnValueHandler extends AbstractMessageConverterMethodProcessor implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private static final boolean romePresent =
            ClassUtils.isPresent("com.rometools.rome.feed.WireFeed",
                    WebMvcConfigurationSupport.class.getClassLoader());

    private static final boolean jaxb2Present =
            ClassUtils.isPresent("javax.xml.bind.Binder",
                    WebMvcConfigurationSupport.class.getClassLoader());

    private static final boolean jackson2Present =
            ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper",
                    WebMvcConfigurationSupport.class.getClassLoader()) &&
                    ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator",
                            WebMvcConfigurationSupport.class.getClassLoader());

    private static final boolean jackson2XmlPresent =
            ClassUtils.isPresent("com.fasterxml.jackson.dataformat.xml.XmlMapper",
                    WebMvcConfigurationSupport.class.getClassLoader());

    private static final boolean jackson2SmilePresent =
            ClassUtils.isPresent("com.fasterxml.jackson.dataformat.smile.SmileFactory",
                    WebMvcConfigurationSupport.class.getClassLoader());

    private static final boolean jackson2CborPresent =
            ClassUtils.isPresent("com.fasterxml.jackson.dataformat.cbor.CBORFactory",
                    WebMvcConfigurationSupport.class.getClassLoader());

    private static final boolean gsonPresent =
            ClassUtils.isPresent("com.google.gson.Gson",
                    WebMvcConfigurationSupport.class.getClassLoader());

    private static final boolean jsonbPresent =
            ClassUtils.isPresent("javax.json.bind.Jsonb",
                    WebMvcConfigurationSupport.class.getClassLoader());
    private static List<HttpMessageConverter<?>> list = new ArrayList();


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    static{
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setWriteAcceptCharset(false);
        list.add(new ByteArrayHttpMessageConverter());
        list.add(stringHttpMessageConverter);
        list.add(new ResourceHttpMessageConverter());
        list.add(new ResourceRegionHttpMessageConverter());
        list.add(new SourceHttpMessageConverter<>());
        list.add(new AllEncompassingFormHttpMessageConverter());
    }

    private void addNeedApplicationContextMessageConverter(){
        if (romePresent) {
            list.add(new AtomFeedHttpMessageConverter());
            list.add(new RssChannelHttpMessageConverter());
        }
        if (jackson2XmlPresent) {
            Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.xml();
            if (this.applicationContext != null) {
                builder.applicationContext(this.applicationContext);
            }
            list.add(new MappingJackson2XmlHttpMessageConverter(builder.build()));
        }
        else if (jaxb2Present) {
            list.add(new Jaxb2RootElementHttpMessageConverter());
        }

        if (jackson2Present) {
            Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.json();
            if (this.applicationContext != null) {
                builder.applicationContext(this.applicationContext);
            }
            list.add(new MappingJackson2HttpMessageConverter(builder.build()));
        }
        else if (gsonPresent) {
            list.add(new GsonHttpMessageConverter());
        }
        else if (jsonbPresent) {
            list.add(new JsonbHttpMessageConverter());
        }

        if (jackson2SmilePresent) {
            Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.smile();
            if (this.applicationContext != null) {
                builder.applicationContext(this.applicationContext);
            }
            list.add(new MappingJackson2SmileHttpMessageConverter(builder.build()));
        }
        if (jackson2CborPresent) {
            Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.cbor();
            if (this.applicationContext != null) {
                builder.applicationContext(this.applicationContext);
            }
            list.add(new MappingJackson2CborHttpMessageConverter(builder.build()));
        }
    }


    public MyHandlerMethodReturnValueHandler(){
        super(list);
        addNeedApplicationContextMessageConverter();
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        if(BaseResponse.class.isAssignableFrom(returnType.getMethod().getReturnType())){
            return true;
        }
        return false;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        Map<String,Object> result = new HashMap<>();
        result.put("code", "0");
        result.put("message",returnValue);
        mavContainer.setRequestHandled(true);
        ServletServerHttpRequest inputMessage = createInputMessage(webRequest);
        ServletServerHttpResponse outputMessage = createOutputMessage(webRequest);
        writeWithMessageConverters(result, returnType, inputMessage, outputMessage);

    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return null;
    }
}
