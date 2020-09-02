package com.atomikos.service;

import com.atomikos.bean.User;
import com.atomikos.dao.Dao1;
import com.atomikos.dao2.Dao2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: yyl
 * @Date: 2019/2/12 20:49
 */
@Service
public class MyService {
    @Autowired
    private Dao1 dao1;
    @Autowired
    private Dao2 dao2;
    @Transactional(rollbackFor = Exception.class)
    public void testAtomikos(){
        User user = new User();
        user.setAge(12);
        user.setName("yyl1");
        user.setErrorMsg("test1");
        user.setId("1");
        if(true){
            throw new RuntimeException();
        }
        User user1 = new User();
        user1.setAge(13);
        user1.setName("yyl2");
        user1.setErrorMsg("test2");
        user1.setId("2");
        dao1.insert(user);
        dao2.insert(user1);
    }
}
