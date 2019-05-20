package cn.itcast;

import cn.itcast.demo.QueueProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * test
 * hasee
 * 2018/12/5
 * 21:50
 *
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-activemq-producer.xml")
public class test {

    @Autowired
    private QueueProducer queueProducer;

    @Test
    public void testSendMessage(){
        queueProducer.sendTextMessage("hello,world");
    }

}
