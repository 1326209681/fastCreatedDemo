package cn.itcast.demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * QueueProducer
 * hasee
 * 2018/12/5
 * 20:25
 *
 * @Version 1.0
 **/
/*
 *
 * 队列模式：一对一，生产者
 * */
public class QueueProducer {
    public static void main(String[] args) throws JMSException {
        //1. 创建连接工厂
        ActiveMQConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
        //2.创建连接
        Connection connection=connectionFactory.createConnection();
        //3.启动连接
        connection.start();
        //4.获取Session（会话对象） 参数1： 是否启动事务，如果选择启动是手动提交，false是自动提交
        //参数2：消息确认方式
        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建队列对象
        Queue queue=session.createQueue("test-queue");
        //6.创建消息生产者对象
        MessageProducer producer=session.createProducer(queue);
        //7.创建消息对象（文本消息）
        TextMessage textMessage=session.createTextMessage("欢迎来到JMS的世界");
        //8.发送消息
        producer.send(textMessage);
        //9.关闭资源
        producer.close();
        session.close();
        connection.close();
    }
}
