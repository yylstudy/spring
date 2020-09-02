package com.yyl.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/14 20:17
 * @Version: 1.0
 */
@Slf4j
@Service
public class AopService {
    public void doWork(String name){
        log.debug("doWork aop service receive name:{}",name);
        AopService aopService = (AopService)AopContext.currentProxy();
        aopService.doInnerWork(name);
    }
    public void doInnerWork(String name){
        log.debug("doInnerWork aop service receive name:{}",name);
    }
}
