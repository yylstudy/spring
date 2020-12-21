package com.yyl.util;

import com.yyl.bean.ApiRequest;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.Charset;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-11-12
 */
public class SignUtil {

    public static String  encrypt(ApiRequest apiRequest) {
        StringBuilder str = new StringBuilder();
        str.append("appId=").append(apiRequest.getAppId());
        str.append("&data=").append(apiRequest.getData());
        str.append("&timestamp=").append(apiRequest.getTimestamp());
        return encryptMD5(str.toString());
    }

    public static String encryptMD5(String data){
        return DigestUtils.md5Hex(data.getBytes(Charset.forName("UTF-8"))).toUpperCase();
    }

    public static void main(String[] args) {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setAppId("1");
        apiRequest.setData("12345");
        apiRequest.setTimestamp(1234L);
        System.out.println(encrypt(apiRequest));
    }
}
