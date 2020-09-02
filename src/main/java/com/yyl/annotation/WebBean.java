package com.yyl.annotation;

import com.yyl.config.spring.WebCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2019/12/18 10:18
 * @Version: 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Conditional(WebCondition.class)
public @interface WebBean {
}
