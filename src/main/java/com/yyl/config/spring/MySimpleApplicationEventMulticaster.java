package com.yyl.config.spring;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.type.AnnotationMetadata;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 自定义事件处理器，这里主要为了达到异步的作用
 *               这里为什么需要实现ImportBeanDefinitionRegistrar接口呢，如果不实现此接口，那么spring容器会通过
 *               @Import 注解自动注册到spring容器中，但是beanName是不可控的，默认是BeanDefinition的getBeanClassName
 *               当前也就是 com.yyl.config.spring.MySimpleApplicationEventMulticaster 在注册SimpleApplicationEventMulticaster
 *               spring容器会检查容器中是否存在beanName为applicationEventMulticaster的SimpleApplicationEventMulticaster
 *               所以这里需要自定义beanName
 *
 *               @Import 注解的类实现ImportBeanDefinitionRegistrar接口和未实现的区别之一就是可以自定义beanName
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/10 10:31
 * @Version: 1.0
 */
public class MySimpleApplicationEventMulticaster extends SimpleApplicationEventMulticaster
        implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        String beanName = "applicationEventMulticaster";
        RootBeanDefinition beanDefinition = new RootBeanDefinition(MySimpleApplicationEventMulticaster.class);
        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = new ThreadPoolExecutor(threads, threads,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        beanDefinition.getPropertyValues().addPropertyValue("taskExecutor",pool);
        registry.registerBeanDefinition(beanName,beanDefinition);

    }
}
