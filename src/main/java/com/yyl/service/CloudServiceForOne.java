package com.yyl.service;

import com.yyl.annotation.CloudService;
import com.yyl.interfaces.CloudServices;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2019/12/18 10:22
 * @Version: 1.0
 */
@CloudService(version = "1.0")
public class CloudServiceForOne implements CloudServices {
    @Override
    public void callServer() {
        System.out.println("this is cloud server for 1.0");
    }
}
