package cn.itcast.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * QueueProducer
 * hasee
 * 2018/12/5
 * 21:32
 *
 * @Version 1.0
 **/
@Component
public class TopicProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination topicTextDestination;
    /*
    *
    * 发送文本信息
    * */
    public  void sendTextMessage(final String text){
        jmsTemplate.send(topicTextDestination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(text);
            }
        });
    }



}
