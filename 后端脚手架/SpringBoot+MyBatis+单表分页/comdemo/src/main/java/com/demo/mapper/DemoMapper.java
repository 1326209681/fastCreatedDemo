package com.demo.mapper;

import com.demo.entity.Teachplan;
import com.demo.entity.ThingUser;
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

    List<Teachplan> findById(String username);

    List<ThingUser> findall();

    ThingUser byId(int i);

    List<ThingUser> findByPage();

    Integer deleteById(int i);
}
