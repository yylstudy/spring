package com.atomikos.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: yyl
 * @Date: 2018/8/30 14:02
 */
@Getter
@Setter
@ToString
public class User extends BaseEntity{
    private String name;
    private int age;
    private String errorMsg;
}
