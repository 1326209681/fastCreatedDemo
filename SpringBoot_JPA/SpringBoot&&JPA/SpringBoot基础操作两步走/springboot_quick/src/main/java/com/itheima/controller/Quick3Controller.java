package com.itheima.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Quick3Controller
 * hasee
 * 2018/12/20
 * 14:56
 *
 * @Version 1.0
 **/

/*
* 这里使用的方式是通过整体的注入，
*       1. 先在类上添加注解：@ConfigurationProperties(prefix = "person")
*       2. 定义两个变量
*               private String name;
*               private String addr;
*       3. 提供这两个变量的get和set方法
*
*   原理： 通过先将配置文件中的数据扫描并存储，通过定义的变量和get和set方法深克隆给它，即可使用配置文件中的数据了；
*         * 适合数据量较多的情况下
* */

@Controller
@ConfigurationProperties(prefix = "person")
public class Quick3Controller {

    private String name;
    private String addr;



    @RequestMapping("/quick3")
    @ResponseBody
    public String quick2(){
        //获得配置文件的信息
        return "name:"+name+",addr="+addr;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr=addr;
    }
}
