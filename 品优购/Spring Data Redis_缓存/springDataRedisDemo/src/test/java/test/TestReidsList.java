package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * TestReidsList
 * hasee
 * 2018/11/27
 * 10:54
 *
 * @Version 1.0
 **/
/*
 * list集合可以储存空值，可以储存重复的值，它与set有一些区别，设置值为leftPush和rightPush,取值为range【它是设定取一个范围内的值，如果需要取全部的值，需要将range[start,end]的范围
 * 设置为比集合的内容大一些即可】
 *
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext-redis.xml")
public class TestReidsList {

    @Autowired
    private RedisTemplate redisTemplate;


    /*
     *
     * 设置值
     * */
    @Test
    public void setValue() {

        redisTemplate.boundListOps("li").leftPush("关羽");
        redisTemplate.boundListOps("li").rightPush("刘备");
        redisTemplate.boundListOps("li").leftPush("张飞");
        redisTemplate.boundListOps("li").leftPush("");
    }

    /*
     * 批量取值
     * */
    @Test
    public void getValue() {
        //获取集合从0-3索引的值
        List list=redisTemplate.boundListOps("li").range(0, 10);
        System.out.println(list);
    }

    /*
    * 单个取值
    *
    * */
    @Test
    public void getValue_index(){
        Object li=redisTemplate.boundListOps("li").index(1);
        System.out.println(li);
    }


    /*
     * 删除值
     * */
    @Test
    public void deleteValue() {
        redisTemplate.delete("li");
    }


}
