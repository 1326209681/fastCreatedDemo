package com.demo.service;

import com.demo.entity.Teachplan;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @program: DemoService
 * @author: 张磊
 * @create: 2019/4/1-23:13
 **/

public interface DemoService {

    PageInfo<Teachplan> queryList(int pageNo, int pageSize);

    List<Teachplan> queryList2(int pageNo, int pageSize);
}
