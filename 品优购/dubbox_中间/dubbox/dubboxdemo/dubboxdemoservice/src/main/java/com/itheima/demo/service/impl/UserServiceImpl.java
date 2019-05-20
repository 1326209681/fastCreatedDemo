package com.itheima.demo.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.demo.service.UserService;

/**
 * UserServiceImpl
 * hasee
 * 2018/11/15
 * 10:18
 *
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getName() {
        return "itheima";
    }
}
