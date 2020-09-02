package com.yyl.config.spring;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl.extend
 * @Description: TODO(这里描述)
 * @Date 2019/7/16 0016
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebProxy {
}
