package com.yyl.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2019/12/24 13:46
 * @Version: 1.0
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    @Pointcut("execution(* com.yyl.service.AopService.doWork(..))")
    public void doWork(){

    }

    @Pointcut("execution(* com.yyl.service.AopService.doInnerWork(..))")
    public void doInnerWork(){

    }
    @Before(value = "doInnerWork()")
    public void doInnerWorkLogBefore(JoinPoint joinPoint){
        log.info("doInnerWork before");
    }

    @Before(value = "doWork()")
    public void logBefore(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        Object obj = joinPoint.getTarget();
        Arrays.stream(args).forEach(arg->log.info("aop 目标方法参数:{}",arg));
        log.debug("aop method info:{}",signature);
        log.debug("aop target object:{}",obj);
        log.info("call server before");
    }
    @After("doWork()")
    public void logAfter(JoinPoint joinPoint){
        log.info("call server after");
    }
    @Around("doWork()")
    public Object logArount(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("around before");
        Object obj = joinPoint.proceed();
        log.info("arount after");
        return obj;
    }
}
