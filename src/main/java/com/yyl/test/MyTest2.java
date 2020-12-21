package com.yyl.test;

import com.alibaba.fastjson.JSONObject;
import com.yyl.bean.ApiRequest;
import com.yyl.util.SignUtil;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-11-12
 */
public class MyTest2 {
    RestTemplate restTemplate = new RestTemplate();
    @Test
    public void test1(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appId","1");
        String data = "{\"memberName\":\"yyl123\"}";
        data = Base64.encodeBase64String(data.getBytes(Charset.forName("UTF-8")));
        Long timestamp = System.currentTimeMillis();
        jsonObject.put("data", data);
        jsonObject.put("timestamp",timestamp);
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setTimestamp(timestamp);
        apiRequest.setAppId("1");
        apiRequest.setData(data);
        String sign = SignUtil.encrypt(apiRequest);
        jsonObject.put("sign",sign);
        String result = restTemplate.postForObject("http://localhost:8082/api/test1/"
                ,jsonObject,String.class);
        System.out.println(result);
    }
}
