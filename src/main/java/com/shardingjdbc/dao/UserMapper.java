package com.shardingjdbc.dao;

import com.shardingjdbc.annotation.MDao;
import com.yyl.bean.User;

import java.util.List;

/**
 * @Author: yyl
 * @Date: 2018/8/30 14:03
 */
@MDao
public interface UserMapper {
    int insert(User user);
    int update(User user);
    List<User> findUser();
}
