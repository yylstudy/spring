package com.yyl.service;

import com.yyl.bean.User;
import com.yyl.page.PageDto;
import com.yyl.page.PageInfo;

import java.util.List;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/20 15:13
 * @Version: 1.0
 */
public interface UserService {
    void insertByJdbcTemplate();
    int insertByMybatis();
    List<User> findAll();
    PageDto<User> findPage(PageInfo pageInfo,String password);
}
