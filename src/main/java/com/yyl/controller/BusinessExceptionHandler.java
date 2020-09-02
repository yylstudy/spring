package com.yyl.controller;

import com.yyl.bean.BaseResponse;
import com.yyl.bean.RestResponse;
import com.yyl.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl.controller
 * @Description: 自定义BusinessException异常返回
 * @Date 2019/8/28 0028
 */
@ControllerAdvice(basePackages = "com.yyl.controller")
@Slf4j
public class BusinessExceptionHandler {
    /**
     * 固定类型异常处理器
     * @param exception
     * @param handlerMethod
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public RestResponse businessExceptionHandler(BusinessException exception, HandlerMethod handlerMethod){
        log.debug("handlerMethod method name:{},bean type:{}",handlerMethod.getMethod().getName(),
                handlerMethod.getBeanType());
        RestResponse response = new RestResponse(exception.getMessage(),exception.getCode());
        return response;
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object exceptionHandler(Exception exception, HandlerMethod handlerMethod){
        if(BaseResponse.class.isAssignableFrom(handlerMethod.getMethod().getReturnType())){
            return new RestResponse(exception.getMessage(),"500");
        }
        ModelAndView view = new ModelAndView("exception");
        view.addObject("error",exception);
        return view;
    }
}
