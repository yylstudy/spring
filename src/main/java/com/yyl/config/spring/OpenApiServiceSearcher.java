package com.yyl.config.spring;

import com.yyl.annotation.ApiService;
import com.yyl.bean.HandlerMethod;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description 查找bean上所有拥有@ApiService的注解
 * @createTime 2020-11-12
 */
@Component
public class OpenApiServiceSearcher implements ApplicationContextAware {
    private static ConcurrentMap<String, HandlerMethod> openApiMethod = new ConcurrentHashMap();
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String[] beanDefinitionNames = applicationContext.getBeanNamesForType(Object.class, false, true);
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = applicationContext.getBean(beanDefinitionName);
            Map<Method, ApiService> annotatedMethods = null;
            annotatedMethods = MethodIntrospector.selectMethods(bean.getClass(),(MethodIntrospector.MetadataLookup) method->
                    AnnotatedElementUtils.findMergedAnnotation(method, ApiService.class));
            if (annotatedMethods==null || annotatedMethods.isEmpty()) {
                continue;
            }
            annotatedMethods.forEach((method,apiService)->{
                if (apiService == null) {
                    return;
                }
                String methodName = apiService.method();
                if (methodName.trim().length() == 0) {
                    throw new RuntimeException("apiService method invalid, for[" + bean.getClass() + "#" + method.getName() + "] .");
                }
                if (openApiMethod.containsKey(methodName)) {
                    throw new RuntimeException("apiService [" + methodName + "] naming conflicts.");
                }
                Class<?>[] parameterClass = method.getParameterTypes();
                if (parameterClass.length != 1) {
                    throw new RuntimeException("apiService method paramter count must = 1 but find "+parameterClass.length);
                }
                method.setAccessible(true);
                openApiMethod.put(methodName,new HandlerMethod(bean,method,parameterClass[0]));
            });
        }
    }

    public static HandlerMethod getHandlerMethod(String methodName){
        return openApiMethod.get(methodName);
    }
}
