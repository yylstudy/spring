package com.yyl.service;

import com.yyl.annotation.WebBean;
import com.yyl.config.spring.WebCondition;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/4/25 19:55
 * @Version: 1.0
 */
@Component
//不是web环境不加载这个bean
@WebBean
@PropertySource("classpath:custom-dir-file.properties")
@Getter
@Setter
public class PropertySourceBean {
    @Value("${age}")
    private String age;
}
