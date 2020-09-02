package com.yyl.service;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;

/**
 * @Description: factoryBean测试
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/9 19:23
 * @Version: 1.0
 */
@Service
public class MyFactoryBeanService implements FactoryBean<MyFactoryBeanImpl> {
    @Override
    public MyFactoryBeanImpl getObject() throws Exception {
        return new MyFactoryBeanImpl();
    }

    @Override
    public Class<MyFactoryBeanImpl> getObjectType() {
        return MyFactoryBeanImpl.class;
    }
}
