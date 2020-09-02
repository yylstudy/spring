package com.yyl.annotation;

import com.yyl.config.spring.CloudCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
//@Conditional注解，可以有多个Condition，如果存在多个的话 多个之间是或者的关系，
//spring在解析时会获取@Conditional中所有的Condition，并根据PriorityOrdered和Ordered接口排序，排序规则为
//AnnotationAwareOrderComparator.sort(conditions);
@Conditional(CloudCondition.class)
@Service
public @interface CloudService {
    String version();
    String area() default "china";
}
