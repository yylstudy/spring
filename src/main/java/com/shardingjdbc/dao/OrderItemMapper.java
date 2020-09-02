package com.shardingjdbc.dao;

import com.shardingjdbc.annotation.MDao;
import com.shardingjdbc.bean.OrderItem;

/**
 * @Author: yyl
 * @Date: 2018/11/14 11:32
 */
@MDao
public interface OrderItemMapper {
    int insertByCustomGenerator(OrderItem orderItem);
    int insertByDefaultGenerator(OrderItem orderItem);

}
