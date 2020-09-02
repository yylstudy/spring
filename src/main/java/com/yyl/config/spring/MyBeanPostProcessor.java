package com.yyl.config.spring;

import com.yyl.annotation.MyPostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 自定义BeanPostProcessor 实现类似@PostConstruct注解的功能
 *               在自定义BeanPostProcessor时 需要返回bean
 *               不能返回null 因为在spring执行器中存在这样的判断
 *               Object current = beanProcessor.postProcessBeforeInitialization(result, beanName);
     * 			 if (current == null) {
     * 				return result;
     *           }
 *           所以如果为空，那么后面的容器的BeanPostProcessor就不执行了
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/2 17:03
 * @Version: 1.0
 */
@Service
public class MyBeanPostProcessor implements   MergedBeanDefinitionPostProcessor {
    private Class<? extends Annotation> clazz;
    private Map<String, List<Method>> methodMap = new ConcurrentHashMap<>();
    public MyBeanPostProcessor(){
        clazz = MyPostConstruct.class;
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        try{
            if(methodMap.containsKey(beanName)){
                for(Method method:methodMap.get(beanName)){
                    method.invoke(bean);
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(beanType);
        for(Method method:methods){
            //方法上包含目标注解
            if(method.isAnnotationPresent(clazz)){
                List<Method> methodss =  methodMap.computeIfAbsent(beanName,key->new ArrayList<>());
                methodss.add(method);
            }
        }
    }
    @Getter
    @Setter
    static class MethodHandler{
        private Method method;
    }
}
