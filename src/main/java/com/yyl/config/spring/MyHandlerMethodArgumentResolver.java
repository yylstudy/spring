package com.yyl.config.spring;

import com.yyl.annotation.ParamStr;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 自定义参数解析器，为字符串添加spring5:前缀
 * @Author: yyl
 * @Date: 2019/1/28 9:38
 */
public class MyHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ParamStr.class);

    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object arg = null;
        ParamStr paramStr = parameter.getParameterAnnotation(ParamStr.class);
        String[] paramValues = webRequest.getParameterValues(paramStr.value());
        if(paramValues==null){
            throw new RuntimeException("param "+paramStr.value()+" can not null");
        }else{
            arg = (paramValues.length == 1 ? "spring5:"+paramValues[0] : paramValues);
        }
        return arg;
    }
}
