package com.yyl.annotation;

import com.yyl.config.spring.MySimpleApplicationEventMulticaster;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 开启异步监听执行
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/10 10:30
 * @Version: 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MySimpleApplicationEventMulticaster.class)
public @interface EnableAsyncListener {
}
