package com.yyl.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 自定义监听器
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/9 20:15
 * @Version: 1.0
 */
@Slf4j
@Component
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {
    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        try{
            for(int i=0;i<5;i++){
                Object source = event.getSource();
                log.debug("receive event source:{}",source);
                Thread.sleep(10);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
