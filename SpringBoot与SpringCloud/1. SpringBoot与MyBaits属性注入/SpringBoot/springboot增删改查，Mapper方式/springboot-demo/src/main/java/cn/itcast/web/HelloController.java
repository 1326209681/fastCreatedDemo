package cn.itcast.web;

import cn.itcast.pojo.User;
import cn.itcast.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

/**
 * HelloController
 * hasee
 * 2019/1/26
 * 11:39
 *
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/user")
public class HelloController {


/*

    @Autowired
    private DataSource dataSource;
*/
    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User hello(@PathVariable("id")Long id){
       return userService.queryById(id);
    }
}
