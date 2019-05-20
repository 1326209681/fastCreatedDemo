package com.itheima.controller;

import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * MybatisController
 * hasee
 * 2018/12/20
 * 16:06
 *
 * @Version 1.0                                                                                                       */
@Controller
public class MybatisController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/quick")
    @ResponseBody
    public List<User> queryUserList(){
        List<User> users=userMapper.queryUserList();
        return users;
    };

}
