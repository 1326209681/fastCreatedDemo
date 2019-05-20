package com.itheima.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * QuickController
 * hasee
 * 2018/12/20
 * 9:29
 *
 * @Version 1.0
 **/


/*
* 这里的注入数据是精准数据
*   1. 优点： 精准和匹配，使用简单快速
*   2. 缺点： 如果数据量较多的时候，导入麻烦；
*
* */
@Controller
public class QuickController {


    @Value("${name}")
    private String name;

    @Value("${person.age}")
    private String age;
    /*
    *
    * 打印普通数据
    * */
    @RequestMapping("/quick")
    @ResponseBody
    public String findOne(){
        return name;
    }

    /*
    * 组合： 普通数据+对象数据
    * */
    @RequestMapping("/quick2")
    @ResponseBody
    public String findTwo(){
        return "姓名"+name+"年龄："+age;
    }
}
