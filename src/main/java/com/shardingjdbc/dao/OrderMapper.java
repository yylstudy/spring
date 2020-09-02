package com.shardingjdbc.dao;

import com.shardingjdbc.annotation.MDao;
import com.shardingjdbc.bean.Order;

import java.util.List;

/**
 * @Author: yyl
 * @Date: 2018/11/1 20:36
 */
@MDao
public interface OrderMapper {
    List<Order> findOrder();
    int insert(Order order);
    int insertByCustomGenerator(Order order);
    int insertByDefaultGenerator(Order order);
    int insertManyValue(Order order);
    List<Order> findTest();

}
