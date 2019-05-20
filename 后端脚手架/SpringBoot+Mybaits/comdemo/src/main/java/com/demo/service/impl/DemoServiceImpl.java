package com.demo.service.impl;

import com.demo.entity.Teachplan;
import com.demo.mapper.DemoMapper;
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

    public List<Teachplan> queryList() {
        return demomapper.findAll();
    }
}
