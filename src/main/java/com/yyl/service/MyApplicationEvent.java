package com.yyl.service;

import org.springframework.context.ApplicationEvent;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/9 20:13
 * @Version: 1.0
 */
public class MyApplicationEvent extends ApplicationEvent {
    public MyApplicationEvent(Object source) {
        super(source);
    }
}
