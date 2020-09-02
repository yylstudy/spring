package com.shardingjdbc.service;

import com.shardingjdbc.dao.OrderItemMapper;
import com.shardingjdbc.dao.OrderMapper;
import com.shardingjdbc.dao.UserMapper;
import com.yyl.bean.User;
import io.shardingsphere.core.keygen.DefaultKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: yyl
 * @Date: 2019/2/20 18:55
 */
@Service
public class TestService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DefaultKeyGenerator defaultKeyGenerator;


    @Transactional(rollbackFor = Exception.class)
    public void insertUser(){
        User user = new User();
//        user.setId("1");
//        user.setName("yyl9");
//        user.setAge(30);
        userMapper.insert(user);
    }

    public List<User> queryUser(){
        return userMapper.findUser();
    }


//    @Transactional(rollbackFor = Exception.class)
//    public void insert(){
//        for(int i=0;i<10;i++){
//            Order order = new Order();
//            Long orderId = (Long)defaultKeyGenerator.generateKey();
//            System.out.println("---------------"+orderId);
//            order.setOrderId(orderId);
//            order.setOrderName("this id order "+i);
//            order.setUserId((long)i);
//            orderMapper.insertByCustomGenerator(order);
//            OrderItem orderItem = new OrderItem();
//            orderItem.setOrderId(orderId);
//            orderItem.setUserId((long)i);
//            orderItem.setItemDesc("this is order item "+i);
//            orderItemMapper.insertByDefaultGenerator(orderItem);
//        }
//    }
}
