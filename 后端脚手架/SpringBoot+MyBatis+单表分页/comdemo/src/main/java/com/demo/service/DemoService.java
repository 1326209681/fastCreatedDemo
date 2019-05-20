package com.demo.service;

import com.demo.entity.Teachplan;
import com.demo.entity.ThingUser;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @program: DemoService
 * @author: 张磊
 * @create: 2019/4/1-23:13
 **/

public interface DemoService {

    PageInfo<Teachplan> queryList(int pageNo, int pageSize);

    void test();


    List<ThingUser> findAll();

    ThingUser findById();

    PageInfo<ThingUser> findByPage();

    Integer deleteById();
}
