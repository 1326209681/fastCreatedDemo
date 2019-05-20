package com.demo.controller;

import com.demo.entity.Teachplan;
import com.demo.entity.ThingUser;
import com.github.pagehelper.PageInfo;
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

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/start")
    public List start(){
        System.out.println("我要查询一些东西啦~~~");
        List<ThingUser> all=demoService.findAll();
        return all;
    }

    /**
     * 根据Id查询
     */
    @RequestMapping("/findById")
    public ThingUser findById(){
        ThingUser entity=demoService.findById();
        return entity;
    }

    /**
     * 分页查询
     */
    @RequestMapping("/findByPage")
    public PageInfo<ThingUser> findByPage(){
        PageInfo<ThingUser> list= demoService.findByPage();
      return list;
    }

    /**
     * 根据指定的id值删除
     */
    @RequestMapping("/deleteById")
    public String deleteById(){
        Integer i=demoService.deleteById();
        String result=i==1?"删除成功":"删除失败";
        return result;
    }

}
