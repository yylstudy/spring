package com.yyl.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/2 16:30
 * @Version: 1.0
 */
@Slf4j
@Service
public class BaseDoSomethingService implements DoSomethingService {

    @Override
    public void doSomething() {
        log.debug("i do nothing");
    }
}
