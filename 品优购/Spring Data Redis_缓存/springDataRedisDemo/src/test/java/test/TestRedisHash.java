package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

/**
 * TestRedisHash
 * hasee
 * 2018/11/27
 * 11:27
 *
 * @Version 1.0
 **/
 
 //hash有大的key，在大的key值下有小的key和value值，所以从来储存一个对象的属性的时候，可以使用它；存值使用put，取值使用keys，values，或者get("index")找出对应的值，使用即可
 //hash的删除分为两种，一种是直接删除大key，直接delete大key的名字即可； 一种是删除大key下的小key，先boundHashOps大key，然后delete小的key值即可；小的key值对应的value值可以是一个值，也可以是一个对象，也可以是一个数组，也可以是一个其他的集合等..
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-redis.xml")
public class TestRedisHash {

    @Autowired
    private RedisTemplate redisTemplate;

    /*
    * 存值
    * */
    @Test
    public void setValue(){

        redisTemplate.boundHashOps("nameHash").put("a","孙悟空");
        redisTemplate.boundHashOps("nameHash").put("b","八戒");
        redisTemplate.boundHashOps("nameHash").put("c","唐僧");
    }
    /*
    * 批量获取值
    * */
    @Test
    public void getValue(){
        Set nameHash=redisTemplate.boundHashOps("nameHash").keys();
        System.out.println(nameHash);
        System.out.println("------------------------------");
        List values=redisTemplate.boundHashOps("nameHash").values();
        System.out.println(values);
    }
    /*
    * 获取指定的值
    * */
    @Test
    public void getValueById(){
        Object o=redisTemplate.boundHashOps("nameHash").get("b");
        System.out.println(o);
    }

    /*
    * 删除指定的ID值
    * */
    @Test
    public void deleteValueById(){
        redisTemplate.boundHashOps("nameHash").delete("a");

    }
    /*
    * 删除整个hash
    * */
    @Test
    public void deleteValue(){
        redisTemplate.delete("nameHash");
    }
}
