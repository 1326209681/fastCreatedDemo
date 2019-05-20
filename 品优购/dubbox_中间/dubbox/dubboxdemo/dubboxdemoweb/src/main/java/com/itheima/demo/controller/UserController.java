package com.itheima.demo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * UserController
 * hasee
 * 2018/11/15
 * 10:51
 *
 * @Version 1.0
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("/showName")
    @ResponseBody
    public String showName(){
        return userService.getName();
    }
}
