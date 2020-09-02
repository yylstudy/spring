package com.yyl.dao;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-08-28
 */
public interface BaseMapper<T> {
    int insert(T t);
}
