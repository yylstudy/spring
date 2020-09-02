package com.yyl.config.spring;

import com.yyl.annotation.EnableImporteanDefinition;
import com.yyl.service.MyImportBeanDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @Description: 实现了ImportBeanDefinitionRegistrar的@Import的类
 *               注解形式改变MyImportBeanDefinition类的属性值
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/2 11:27
 * @Version: 1.0
 */
@Slf4j
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * 手动注册@Import的类到spring容器中
     * @param importingClassMetadata 这个是@Import注解所在类的AnnotationMetadata
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        log.debug("importingClassMetadata:{}",importingClassMetadata);
        Map<String,Object> attributeMap = importingClassMetadata
                .getAnnotationAttributes(EnableImporteanDefinition.class.getName());
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(MyImportBeanDefinition.class);
        String age = (String)attributeMap.get("age");
        String name = (String)attributeMap.get("name");
        rootBeanDefinition.getPropertyValues().add("name",name);
        rootBeanDefinition.getPropertyValues().add("age",age);
        registry.registerBeanDefinition(rootBeanDefinition.getBeanClassName(),rootBeanDefinition);
    }


}
