package com.yyl.config.spring;

import com.yyl.annotation.CloudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2019/12/18 10:19
 * @Version: 1.0
 */
@Slf4j
public class CloudCondition implements Condition {
    /**
     * @Conditional 是否匹配
     * @param context
     * @param metadata
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String,Object> map = metadata.getAnnotationAttributes(CloudService.class.getName());
        //因为@CloudService注解是个复合注解，包含了@Service也就是包含了@Component注解，
        //所以在component-scan扫描到CloudService.class的时候，也会执行此方法
        //因为其通过了扫描的要求（包含@Component注解），此注解本身不包含自己
        //所有这里需要判断是否为空就是排除此注解本身，若不是复合注解，那么是不需要判空的
        if(map!=null){
            String version = (String)map.get("version");
            log.debug("get CloudCondition version:{}",version);
        }
        return true;
    }
}
