package com.yyl.config.spring;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.web.context.support.StandardServletEnvironment;

/**
 * @Description: 是否是web环境，这里涉及到自定义外部配置目录，外部配置文件加载
 *               到classloader是在web.xml的listener中，所以如果不是web环境就不加载
 *               引用外部目录文件的bean
 * @Author: yang.yonglian
 * @CreateDate: 2020/4/25 20:43
 * @Version: 1.0
 */
public class WebCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        if(context.getEnvironment() instanceof StandardServletEnvironment){
            return true;

        }
        return false;
    }
}
