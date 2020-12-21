package com.yyl.service;

import com.alibaba.fastjson.JSONObject;
import com.yyl.annotation.ApiService;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-11-12
 */
public interface OpenApiService {
    @ApiService(method = "test1")
    JSONObject test1(JSONObject param);
}
