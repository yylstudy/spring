package com.yyl.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2019/12/18 10:52
 * @Version: 1.0
 */
@Getter
@Setter
@ToString
public class User implements BaseResponse {
    private String userName;
    private String password;
}
