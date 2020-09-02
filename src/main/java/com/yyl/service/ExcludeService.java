package com.yyl.service;

import com.yyl.annotation.ExcludeAnnotation;
import org.springframework.stereotype.Component;

/**
 * @Description: 可以看到即使有@Component注解，此类也不会被spring扫描到
 *               因为匹配注解的时候，是先查找exclude的注解
 * @Author: yang.yonglian
 * @CreateDate: 2019/12/18 10:31
 * @Version: 1.0
 */
@Component
@ExcludeAnnotation
public class ExcludeService {
}
