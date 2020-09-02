package com.yyl.annotation;

import com.yyl.config.spring.MyWebMvcConfigurer;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-08-31
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MyWebMvcConfigurer.class)
public @interface EnableCustomMvcConfig {
}
