package com.yyl.exception;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl.controller
 * @Description: TODO(这里描述)
 * @Date 2019/7/6 0006
 */
public class BusinessException extends RuntimeException{
    private String code;

    public BusinessException(){
        super();
    }
    public BusinessException(String code,String msg){
        super(msg);
        this.code = code;
    }

    public BusinessException(String code,String msg, Throwable t){
        super(msg,t);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
