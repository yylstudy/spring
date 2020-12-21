package com.yyl.exception;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-11-12
 */
public class ApiException extends RuntimeException{

    private String message;
    private String code;
    public ApiException(String message){
        super(message);
        this.message = message;
    }

    public ApiException(String message, String code){
        super(message);
        this.code = code;
        this.message = message;
    }
    public ApiException(String message, String code, Throwable cause){
        super(message,cause);
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
