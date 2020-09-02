package com.yyl.service;

import com.yyl.annotation.MyPostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/2 16:32
 * @Version: 1.0
 */
@Service
@Slf4j
public class SaySomethingService implements DoSomethingService {
    private ExecutorService pool;
    @PostConstruct
    public void test1(){
        pool = Executors.newFixedThreadPool(10);
    }
    @MyPostConstruct
    public void test2(){
        log.debug("custom MyPostConstruct suceess");
    }
    @Override
    public void doSomething() {
        log.debug("i am fujianren");
    }
}
