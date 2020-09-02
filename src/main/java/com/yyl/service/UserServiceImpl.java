package com.yyl.service;

import com.yyl.bean.User;
import com.yyl.dao.UserMapper;
import com.yyl.page.PageDto;
import com.yyl.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2020/1/20 15:13
 * @Version: 1.0
 */
@Service
public class UserServiceImpl extends BaseService<User>  implements UserService {
    @Autowired
    private UserDaoForJdbcTemplate userDaoForJdbcTemplate;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private UserMapper userMapper;

    /**
     * 这里可以看到，外部存在事务，那么即使使用TransactionTemplate，
     * 整体来说也是同一个事务，因为TransactionTemplate的传播特性默认是
     * TransactionDefinition.PROPAGATION_REQUIRED
     */
//    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertByJdbcTemplate() {
        transactionTemplate.execute(status->{
            userDaoForJdbcTemplate.insert();
            userDaoForJdbcTemplate.update();
            return null;
        });
        if(true){
            throw new RuntimeException();
        }
        userDaoForJdbcTemplate.insert();
        userDaoForJdbcTemplate.update();
    }

    @Override
    public int insertByMybatis() {
        return 0;
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public PageDto<User> findPage(PageInfo pageInfo,String password) {
        return userMapper.findPage(pageInfo,password);
    }
}
