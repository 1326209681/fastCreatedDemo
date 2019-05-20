package cn.itcast.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息生产者
 * @author Administrator
 */
@RestController
public class QueueController {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("/send")
    public void send(String text){
        jmsMessagingTemplate.convertAndSend("itcast", text);
    }

    @RequestMapping("/sendmap")
    public void sendMap(){
        Map map=new HashMap<>();
        map.put("mobile","18181818147");
        map.put("content","双十一秒杀开始啦~");
        jmsMessagingTemplate.convertAndSend("itcast_map",map);
    }
}