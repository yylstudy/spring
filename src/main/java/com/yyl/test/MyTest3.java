package com.yyl.test;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyl.bean.ApiRequest;
import com.yyl.util.SignUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-11-12
 */
public class MyTest3 {
    public static void main(String[] args) {
        try{
            String ss = "<JSONObject><data>eyJtZW1iZXJOYW1lIjoieXlsMTIzIn0=</data><appId>1</appId>" +
                    "<sign>FEF317B13E0C9A90B6AA33C477B59DD3</sign><timestamp>1605184094681</timestamp></JSONObject>";
            new ObjectMapper().
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
