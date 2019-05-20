package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

/*
* set是一个集合的储存对象，且非空不唯一；储存的时候使用add方法，取的时候使用member，删除的时候使用delete
*
* */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-redis.xml")
public class TestRedisSet {

    @Autowired
    private RedisTemplate redisTemplate;

    /*
    *
    * 设置值
    * */
    @Test
    public void setValue(){
        redisTemplate.boundSetOps("nameSet").add("诸葛亮");
        redisTemplate.boundSetOps("nameSet").add("庞统");
        redisTemplate.boundSetOps("nameSet").add("曹操");
    }


    /*
    * 获取值
    * */
    @Test
    public void getValue(){
        Set nameSet=redisTemplate.boundSetOps("nameSet").members();
        System.out.println(nameSet);
    }


    /*
    * 删除值
    * */
    @Test
    public void deleteValue(){
        redisTemplate.delete("nameSet");

    }
}
