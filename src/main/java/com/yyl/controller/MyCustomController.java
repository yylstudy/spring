package com.yyl.controller;

import com.yyl.config.spring.MyRequestMapping;
import com.yyl.config.spring.WebProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/7/16 0016
 */
@Component
@WebProxy
public class MyCustomController {
    @MyRequestMapping
    @ResponseBody
    public String test1(){
        return "shshhshsh";
    }
}
