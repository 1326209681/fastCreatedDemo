package cn.itcast.demo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Consumer
 * hasee
 * 2018/12/8
 * 18:56
 *
 * @Version 1.0
 **/
@Component
public class Consumer {
    @JmsListener(destination = "itcast")
    public  void readMessage(String text){
        System.out.println("接收到消息:"+text);
    }

    @JmsListener(destination = "itcast_map")
    public void readMap(Map map){
        System.out.println(map);
    }
}
