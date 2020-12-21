package com.yyl.bean;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-11-12
 */
public class HandlerMethod {
    public HandlerMethod(Object bean, Method method, Class<?> parameterType) {
        this.bean = bean;
        this.method = method;
        this.parameterType = parameterType;
    }

    /**
     * spring bean
     */
    private Object bean;
    /**
     * handler method
     */
    private Method method;

    private Class<?> parameterType;

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class<?> getParameterType() {
        return parameterType;
    }

    public void setParameterType(Class<?> parameterType) {
        this.parameterType = parameterType;
    }

    public Object invoke(Object param){
        return ReflectionUtils.invokeMethod(method,bean,param);
    }
}
