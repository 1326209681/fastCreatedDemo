package com.demo.mapper;

import com.demo.entity.Teachplan;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: demoMapper
 * @author: 张磊
 * @create: 2019/4/1-23:32
 **/
@Repository
public interface DemoMapper {

    List<Teachplan> findAll();

}
