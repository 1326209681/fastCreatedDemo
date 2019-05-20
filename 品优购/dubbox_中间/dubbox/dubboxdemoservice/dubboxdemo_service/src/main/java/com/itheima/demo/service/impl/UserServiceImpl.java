package com.itheima.demo.service.impl;

import com.itheima.demo.service.UserService;
import com.alibaba.dubbo.config.annotation.Service;
/**
 * UserServiceImpl
 * hasee
 * 2018/11/15
 * 9:54
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
