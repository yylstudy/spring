package com.yyl.service;

import com.yyl.annotation.IncludeAnnotation;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 此类可以被spring扫描到，因为自定义了扫描注解
 * @Author: yang.yonglian
 * @CreateDate: 2019/12/18 10:29
 * @Version: 1.0
 */
@IncludeAnnotation
@Slf4j
public class IncludeService {
    public IncludeService(){
        log.debug("load IncludeService success ");
    }
}
