package cn.itcast.demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * QueueConsumer
 * hasee
 * 2018/12/5
 * 20:53
 *
 * @Version 1.0
 **/
/*
*
* 队列模式：一对一，消费者
* */
public class QueueConsumer {
    public static void main(String[] args) throws JMSException, IOException {
        //创建工厂
        ActiveMQConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
        //创建连接对象
        Connection connection=connectionFactory.createConnection();
        //启动连接
        connection.start();
        //获取Session（会话对象）
        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列对象
        Queue queue=session.createQueue("test-queue");
        //创建消息的消费者对象
        MessageConsumer consumer=session.createConsumer(queue);
        //设置监听
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage=(TextMessage)message;
                try {
                    System.out.println("提取的消息"+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //8.等待键盘的输入
        System.in.read();
        //9.关闭资源
        consumer.close();
        session.close();
        connection.close();
    }

}
