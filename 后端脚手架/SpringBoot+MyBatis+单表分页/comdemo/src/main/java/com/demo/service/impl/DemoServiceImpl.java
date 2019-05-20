package com.demo.service.impl;

import com.demo.entity.Teachplan;
import com.demo.entity.ThingUser;
import com.demo.mapper.DemoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.service.DemoService;

import java.util.List;

/**
 * @program: DemoServiceImpl
 * @author: 张磊
 * @create: 2019/4/1-23:14
 **/
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demomapper;


    @Override
    public PageInfo<Teachplan> queryList(int pageNo, int pageSize) {

            PageHelper.startPage(pageNo,pageSize);
            List<Teachplan> blogList = demomapper.findAll();
            PageInfo<Teachplan> pageInfo = new PageInfo<Teachplan>(blogList);
            return pageInfo;
        }

        @Override
    public void test(){
            List<Teachplan> list=demomapper.findById("张三");
            System.out.println(list.toString());
        }

    @Override
    public List<ThingUser> findAll() {
     List<ThingUser>  list= demomapper.findall();
        System.out.println(list.toString());
        return list;
    }

    @Override
    public ThingUser findById() {
        ThingUser user=demomapper.byId(1);
        System.out.println(user);
        return user;
    }

    @Override
    public PageInfo<ThingUser> findByPage() {
       PageHelper.startPage(1,10);
        List<ThingUser> list=demomapper.findByPage();
        PageInfo<ThingUser> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Integer deleteById() {
        int i=demomapper.deleteById(1);
        System.out.println(i);
        return i;
    }

}
