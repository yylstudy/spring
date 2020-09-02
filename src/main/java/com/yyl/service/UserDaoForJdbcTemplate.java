package com.yyl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/20 15:20
 * @Version: 1.0
 */
@Repository
public class UserDaoForJdbcTemplate {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(){
        return jdbcTemplate.update("insert into sys_user values('yyl1','password1')");
    }
    public int update(){
        return jdbcTemplate.update("update sys_user set username='yyl11'");
    }
}
