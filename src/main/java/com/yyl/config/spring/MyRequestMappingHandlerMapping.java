package com.yyl.config.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-08-29
 */
@Component
@Slf4j
public class MyRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
    private RequestMappingInfo.BuilderConfiguration config = new RequestMappingInfo.BuilderConfiguration();
    @Override
    protected boolean isHandler(Class<?> beanType) {
        return (AnnotatedElementUtils.hasAnnotation(beanType, WebProxy.class));
    }

    /**
     * 重写该方法，RequestMappingHandlerMapping注册到soring容器时是0，
     * 这里改为4 表示一个地址先经过RequestMappingHandlerMapping解析如果不存在，则再通过
     * MyRequestMappingHandlerMapping解析
     * @return
     */
    @Override
    public int getOrder() {
        return -1;
    }

    public static void main(String[] args) {
        String ss = "com.yyl";
        ss = ss.replaceAll("\\.","/");
        System.out.println(ss);
    }

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        MyRequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(method, MyRequestMapping.class);
        //创建RequestMappingInfo对象
        return (requestMapping != null ? createMyRequestMappingInfo(handlerType,method,requestMapping, null) : null);
    }

    protected RequestMappingInfo createMyRequestMappingInfo(Class<?> handlerType,Method method, MyRequestMapping myRequestMapping,
                                                            @Nullable RequestCondition<?> customCondition) {
        String url = (handlerType.getName()+"."+method.getName()).replaceAll("\\.","/");
        log.debug("url:{}",url);
        RequestMappingInfo.Builder builder = RequestMappingInfo
                .paths(resolveEmbeddedValuesInPatterns(new String[]{url}))
                .methods(myRequestMapping.method())
                .params(myRequestMapping.params())
                .headers(myRequestMapping.headers())
                .consumes(myRequestMapping.consumes())
                .produces(myRequestMapping.produces())
                .mappingName(myRequestMapping.name());
        if (customCondition != null) {
            builder.customCondition(customCondition);
        }
        return builder.options(this.config).build();
    }
}
