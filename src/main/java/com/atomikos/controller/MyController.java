package com.atomikos.controller;

import com.atomikos.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: yyl
 * @Date: 2019/2/12 20:51
 */
@Controller
@RequestMapping("/atomikos")
public class MyController {
    @Autowired
    private MyService myService;
    @RequestMapping("/test1")
    public String test1(){
        myService.testAtomikos();
        return "11";
    }
}
