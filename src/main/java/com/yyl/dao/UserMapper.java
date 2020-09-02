package com.yyl.dao;

import com.yyl.annotation.MyRepository;
import com.yyl.bean.User;
import com.yyl.page.PageDto;
import com.yyl.page.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/2/4 10:10
 * @Version: 1.0
 */
@MyRepository
public interface UserMapper extends BaseMapper<User>{
    List<User> findAll();
    PageDto<User> findPage(@Param("pageInfo") PageInfo pageInfo, @Param("password")String password);
}
