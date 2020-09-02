package com.yyl.config.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/9 20:32
 * @Version: 1.0
 */
@Component
@Slf4j
public class MyContextRefreshListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Object obj = event.getSource();
        log.debug("event source is ApplicationContext?:{}",obj instanceof ApplicationContext);
    }
}
