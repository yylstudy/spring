package com.yyl.controller;

import com.alibaba.fastjson.JSONObject;
import com.yyl.bean.ApiRequest;
import com.yyl.bean.HandlerMethod;
import com.yyl.config.spring.OpenApiServiceSearcher;
import com.yyl.exception.ApiException;
import com.yyl.util.SignUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-11-12
 */
@Controller
public class ApiController {
    @Value("${api.appId}")
    private String conAppId;
    private UrlPathHelper urlPathHelper = new UrlPathHelper();
    @RequestMapping("test2222")
    @ResponseBody
    public String test2(){
        return "success";
    }

    @RequestMapping("api/**")
    @ResponseBody
    public Object api(HttpServletRequest request) throws Exception {
        String lookupPath = urlPathHelper.getLookupPathForRequest(request);
        String parameterStr = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
        ApiRequest apiRequest = JSONObject.parseObject(parameterStr, ApiRequest.class);
        String appId = apiRequest.getAppId();
        if(!conAppId.equals(appId)){
            throw new ApiException("appId不正确");
        }
        if(StringUtils.isEmpty(parameterStr)){
            throw new ApiException("请求参数为空");
        }
        String sign = apiRequest.getSign();
        String encrypt = SignUtil.encrypt(apiRequest);
        if(!StringUtils.equals(sign,encrypt)){
            throw new ApiException("参数签名校验失败");
        }
        String requestMethod = lookupPath.substring(5);
        if(requestMethod.endsWith("/")){
            requestMethod = requestMethod.substring(0,requestMethod.length()-1);
        }
        HandlerMethod handlerMethod = OpenApiServiceSearcher.getHandlerMethod(requestMethod);
        if(handlerMethod==null){
            throw new ApiException("url:"+requestMethod+"有误");
        }
        Object param;
        try{
            String dataStr = apiRequest.getData();
            dataStr = StringUtils.toEncodedString(Base64.decodeBase64(dataStr),Charset.forName("UTF-8"));
            param = JSONObject.parseObject(dataStr,handlerMethod.getParameterType());
        }catch (Exception e){
            throw new ApiException("参数转换错误");
        }
        Object result = handlerMethod.invoke(param);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","0");
        jsonObject.put("message","接口调用成功");
        jsonObject.put("data",result);
        return jsonObject;
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handlerException(Exception exception){
        JSONObject jsonObject = new JSONObject();
        String code = "-1";
        if(exception instanceof ApiException){
            ApiException apiException = (ApiException)exception;
           if(StringUtils.isNotEmpty(apiException.getCode())){
               code = apiException.getCode();
            }
        }
        String message = exception.getMessage();
        if(StringUtils.isEmpty(message)){
            message = exception.toString();
        }
        jsonObject.put("code",code);
        jsonObject.put("message",message);
        return jsonObject;
    }
}
