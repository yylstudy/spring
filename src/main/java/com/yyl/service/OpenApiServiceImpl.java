package com.yyl.service;

import com.alibaba.fastjson.JSONObject;
import com.yyl.annotation.ApiService;
import org.springframework.stereotype.Service;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-11-12
 */
@Service
public class OpenApiServiceImpl implements OpenApiService{
   @Override
    public JSONObject test1(JSONObject param){
        System.out.println(param);
        param.put("age","30");
        return param;
    }

}
