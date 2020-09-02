package com.shardingjdbc.controller;

import com.shardingjdbc.bean.Order;
import com.shardingjdbc.dao.OrderItemMapper;
import com.shardingjdbc.dao.OrderMapper;
import com.shardingjdbc.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: yyl
 * @Date: 2018/9/13 14:32
 */
@Controller
public class TestController {
    private Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private DefaultListableBeanFactory defaultListableBeanFactory;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private TestService testService;

    /**
     * sharding-jdbc 写测试
     * @return
     */
    @RequestMapping("/insertUser")
    @ResponseBody
    public String insertUser(){
        try{
            testService.insertUser();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * sharding-jdbc 读写分离   读测试
     * @return
     */
    @RequestMapping("/queryUser")
    @ResponseBody
    public String queryUser(){
        try{
            logger.debug(String.valueOf(testService.queryUser()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * sharding-jdbc 分库分表测试
     * @return
     */
    @RequestMapping("/insertByCustomGenerator")
    @ResponseBody
    public String insertByCustomGenerator(){
        Order order = new Order();
        order.setOrderId(2L);
        order.setOrderName("order1");
        order.setUserId(2L);
        orderMapper.insert(order);
        return null;
    }

    /**
     * sharding-jdbc 读写分离+分库分表测试
     * @return
     */
    @RequestMapping("/insertByDefaultGenerator")
    @ResponseBody
    public String insertByDefaultGenerator(){
        try{
            Order order = new Order();
            order.setOrderName("order2");
            order.setUserId(2L);
            orderMapper.insertByDefaultGenerator(order);

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * sharding-jdbc 读写分离+分库分表 插入多值测试
     * @return
     */
    @RequestMapping("/insertManyValue")
    @ResponseBody
    public String insertManyValue(){
        try{
            Order order = new Order();
            order.setOrderName("order2");
            order.setUserId(2L);
            orderMapper.insertManyValue(order);

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
