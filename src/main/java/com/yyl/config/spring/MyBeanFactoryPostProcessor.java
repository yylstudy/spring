package com.yyl.config.spring;

import com.yyl.service.BaseDoSomethingService;
import com.yyl.service.DoSomethingService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: 自定义BeanFactoryPostProcessor
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/2 16:21
 * @Version: 1.0
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        if(!(configurableListableBeanFactory instanceof DefaultListableBeanFactory)){
            throw new RuntimeException(" 当前BeanFactory不是DefaultListableBeanFactory的子类");
        }
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)configurableListableBeanFactory;
        //自定义了BeanName生成器
        String baseBeanName = "yyl"+BaseDoSomethingService.class.getName();
        String[] services = beanFactory.getBeanNamesForType(DoSomethingService.class);
        if(services.length>2){
            throw new RuntimeException(" DoSomethingService 接口最多存在两个实现类");
        }
        if(services.length==2){
            //存在多个，则删除BaseService，这样DosomethingService接口只会存在一个实例，可以使用@Autowired注解
            beanFactory.removeBeanDefinition(baseBeanName);
        }
    }
}
