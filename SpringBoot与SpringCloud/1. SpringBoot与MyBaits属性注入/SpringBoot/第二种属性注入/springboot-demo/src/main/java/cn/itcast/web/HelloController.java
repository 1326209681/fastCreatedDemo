package cn.itcast.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * HelloController
 * hasee
 * 2019/1/26
 * 11:39
 *
 * @Version 1.0
 **/
@RestController
public class HelloController {

    @Autowired
    private DataSource dataSource;


    @GetMapping("/hello")
    public String hello(){
        return "hello,spring boot !";
    }
}
