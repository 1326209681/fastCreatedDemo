package com.demo.controller;

import com.demo.entity.Teachplan;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.demo.service.DemoService;

import java.util.List;

/**
 * @program: demoController
 * @author: 张磊
 * @create: 2019/4/1-23:09
 **/
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/hello")
    public String sayHello(){
        System.out.println("我进来啦");
        return "hello,world!";
    }

    @RequestMapping("/query")
    public PageInfo<Teachplan> queryList(@RequestParam int pageNo, @RequestParam int pageSize){
        return demoService.queryList(pageNo,pageSize);
    }
    @RequestMapping("/query2")
    public List<Teachplan> queryList2(@RequestParam int pageNo, @RequestParam int pageSize){
        return demoService.queryList2(pageNo,pageSize);
    }
}
