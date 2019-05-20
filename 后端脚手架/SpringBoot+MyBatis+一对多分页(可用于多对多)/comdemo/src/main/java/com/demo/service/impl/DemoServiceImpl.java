package com.demo.service.impl;

import com.demo.entity.Teachplan;
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

      //  List<Teachplan> all=demomapper.findAll(currentPageNo, pageSize);
    /*    int pageNo;
        if (currentPageNo==0){
           pageNo=(pageSize-1)*currentPageNo;
        }else {
            pageNo=(pageSize)*currentPageNo;
        }
        List<Teachplan> all=demomapper.findAll(pageNo, pageSize);
        System.out.println(all.toString());
        PageInfo<Teachplan> pageInfo = new PageInfo<Teachplan>(all);
        return pageInfo;*/
    }

    @Override
    public List<Teachplan> queryList2(int currentPageNo, int pageSize) {
     /*   int pageNo;
        if (currentPageNo==0){
            pageNo=(pageSize-1)*currentPageNo;
        }else {
            pageNo=(pageSize)*currentPageNo;
        }
        List<Teachplan> all=demomapper.findAll(pageNo, pageSize);
        System.out.println(all.toString());*/

        return null;
    }


}
