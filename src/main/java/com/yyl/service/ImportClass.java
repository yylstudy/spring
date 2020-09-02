package com.yyl.service;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 未实现ImportSelector和ImportBeanDefinitionRegistrar接口的@Import注解的类
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/2 10:36
 * @Version: 1.0
 */
@Slf4j
public class ImportClass {
    public void say(){
        log.debug("i am register spring from import ");
    }
}
