package com.itheima.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * QuickController
 * hasee
 * 2018/12/20
 * 10:38
 *
 * @Version 1.0
 **/
@RestController
public class QuickController {

    @Value("${name}")
    private String name;



    @RequestMapping("/quick2")
    public String quick(){
        return name;
    }
}
