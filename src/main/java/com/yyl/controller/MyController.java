package com.yyl.controller;

import com.yyl.annotation.ParamStr;
import com.yyl.annotation.WebBean;
import com.yyl.bean.BaseResponse;
import com.yyl.bean.User;
import com.yyl.exception.BusinessException;
import com.yyl.service.PropertySourceBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

/**
 * @Author: yyl
 * @Date: 2018/11/27 20:49
 */
@Controller
@Slf4j
@WebBean
public class MyController {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private Environment environment;
    @Autowired
    private PropertySourceBean propertySourceBean;

    /**
     * 自定义参数解析器测试
     * @return
     */
    @RequestMapping("/test1")
    @ResponseBody
    public String test1(@ParamStr("name")String name){
        log.debug("custom annotation paramStr:{}",name);
        return "success";
    }

    /**
     * 自定义配置文件目录测试
     * @return
     */
    @RequestMapping("/test2")
    @ResponseBody
    public String test2(){
        log.debug(propertySourceBean.toString());
        log.debug("age is:{}",propertySourceBean.getAge());
        log.info("environment:{}",environment);
        return null;
    }

    /**
     * spring 全局异常测试
     * @return
     */
    @RequestMapping("/test3")
    public String test3(){
        throw new RuntimeException("xxxxx");
    }
    /**
     * spring 全局异常测试
     * @return
     */
    @RequestMapping("/test4")
    public BaseResponse test4(){
        throw new RuntimeException("hheheheh");
    }
    /**
     * springmvc BusinessException
     * @return
     */
    @RequestMapping("/test5")
    public String test5(){
        throw new BusinessException("400","测试异常");
    }

    /**
     * 自定义返回值解析器
     * @return
     */
    @RequestMapping("/test6")
    public User test6(){
        User user = new User();
        user.setUserName("yyl");
        user.setPassword("123");
        return user;
    }

    /**
     * 自定义返回值解析器
     * @return
     */
    @RequestMapping("/test7")
    public User test7(){
        throw new NullPointerException("hahaha");
    }


    /**
     * 当当前类存在@ExceptionHandler方法时，优先使用，否则再使用容器中的@ControllerAdvice
     * @param exception
     * @param handlerMethod
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public String exceptionHandler(Exception exception, HandlerMethod handlerMethod){
        log.debug(exception.toString());
        log.debug(handlerMethod.getBean().getClass().toString());
        return "2";
    }


}
