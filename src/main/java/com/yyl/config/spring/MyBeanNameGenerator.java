package com.yyl.config.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2019/12/18 9:32
 * @Version: 1.0
 */
public class MyBeanNameGenerator implements BeanNameGenerator {
    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        return "yyl"+definition.getBeanClassName();
    }
}
