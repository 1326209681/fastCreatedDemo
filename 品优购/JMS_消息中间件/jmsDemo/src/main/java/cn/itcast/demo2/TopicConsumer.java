package cn.itcast.demo2;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * TopicConsumer
 * hasee
 * 2018/12/5
 * 21:10
 *
 * @Version 1.0
 **/

public class TopicConsumer {
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
        Topic topic=session.createTopic("test-topic");
        //创建消息的消费者对象
        MessageConsumer consumer=session.createConsumer(topic);
        //设置监听
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage=(TextMessage)message;
                try {
                    System.out.println("提取的消息1:"+textMessage.getText());
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
