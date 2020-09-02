package com.yyl.service;

import com.yyl.dao.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-08-28
 */
public class BaseService<T> {
    @Autowired
    protected BaseMapper<T> baseMapper;

    public int insert(T t){
        return baseMapper.insert(t);
    }
}
