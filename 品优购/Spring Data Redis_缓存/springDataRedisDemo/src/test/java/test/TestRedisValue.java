package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * TestRedisValue
 * hasee
 * 2018/11/27
 * 10:17
 *
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)             //导入测试依赖
@ContextConfiguration(locations = "classpath:spring/applicationContext-redis.xml")      //导入配置依赖
public class TestRedisValue {
    //导入springData Redis的操作对象
    @Autowired
    private RedisTemplate redisTemplate;
    /*
    *
    * 设置值
    * */
    @Test
    public void setValues(){
        redisTemplate.boundValueOps("name").set("黑马诸葛亮");
    }
    /*
    *
    * 获取值
    * */
    @Test
    public void getValues(){
        Object value=redisTemplate.boundValueOps("name").get();
        System.out.println(value);
    }

    /*
    * 删除值
    * */
    @Test
    public void deleteValue(){
        redisTemplate.delete("name");
    }
}
