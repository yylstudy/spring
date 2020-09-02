package com.yyl.config.spring;

import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

import java.util.List;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description 注解方式自定义spring-mvc配置
 * @createTime 2020-08-31
 */
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    /**
     * 自定义参数解析器
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        MyHandlerMethodArgumentResolver resolver = new MyHandlerMethodArgumentResolver();
        resolvers.add(resolver);
    }

    /**
     * 自定义返回值解析器
     * @param handlers
     */
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        MyHandlerMethodReturnValueHandler handler = new MyHandlerMethodReturnValueHandler();
        handlers.add(handler);
    }

    /**
     * 自定义spring 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        MyInterceptor myInterceptor = new MyInterceptor();
        MappedInterceptor ss = new MappedInterceptor(null,new String[]{"*"},myInterceptor);
        registry.addInterceptor(ss);
    }
}
