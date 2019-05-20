package com.xuecheng.test.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Consumer01
 * hasee
 * 2019/1/2
 * 9:37
 *
 * @Version 1.0
 **/
/*
* 入门程序消费者
*
*
* */
public class Consumer01 {
    //队列
    private static final String QUEUE="helloworld";

    public static void main(String[] args) throws IOException, TimeoutException {

        //通过连接工厂创建新的连接和mq建立连接
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //设置虚拟机，一个mq服务可以设置多个虚拟机，每个虚拟机就相当于一个独立的mq
        connectionFactory.setVirtualHost("/");
        //建立新连接
        Connection connection=connectionFactory.newConnection();
        //创建会话通道，生产者和mq服务所有通信都在channel通道中完成
        Channel channel=connection.createChannel();
        /*
        * 生产者和消费者都要声明队列，
        *   1. 消费者声明队列是因为要接收信息；
        *   2. 生产者声明队列是因为要确认消息是否被对方接收；
        *   3. 因为有可能会存在，生产者发送消息而消费者还未启动，消费者启动而生产者未启动等情况
        *
        *
        * */
        /*
        * 监听队列：
        *   声明队列,如果队列在mq中没有则要创建
        *   参数：String queue,boolean durable ,boolean exclusive, boolean autoDelete, Map<String,Object> arguments
        *   参数明细：
        *       1. queue 队列名称
        *       2. durable 是否持久化，如果持久化，mq重启后队列还在
        *       3. exclusive  是否独占连接，队列只允许在该连接中访问，如果connection连接关闭则自动删除，如果将此设置true可用于临时队列的创建
        *       4. autoDelete  自动删除，队列不再使用时是否自动删除此队列，如果将此参数和exclusive参数设置为true就可以实现临时队列（队列不用了就自动删除）
        *       5. arguments  参数 ，可以设置一个队列的扩展参数，比如： 可设置存货时间
        *
        *
        * */
        channel.queueDeclare(QUEUE,true,false,false,null);
        //实现消费方法
        DefaultConsumer defaultConsumer=new DefaultConsumer(channel){

            /*
            * 当接收到消息后此方法调用
            * consumerTag: 消费者标签，用来标识消费者的，在监听队列时设置channel.basicConsume
            * envelope:  信封，通过envelope
            * properties:  消息属性
            * body:    消息内容
            * */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //交换机
                String exchange=envelope.getExchange();
                //消息id，mq在channel中用来标识消息的id，可用于确认消息已接收
                long deliveryTag=envelope.getDeliveryTag();
                //消息内容
                String message=new String(body,"utf-8");
                System.out.println("receive message"+message);
            }
        };

        /*
        * 监听队列：
        * 参数： String queue,boolean autoAck,Consumer callback
        * 参数明细：
        *   1. queue  队列名称
        *   2. autoAck  自动回复，当消费者接收到消息后告诉mq消息已接收；
        *   3. callback  消费方法，当消费者接收到到消息要执行的方法
        * */
        channel.basicConsume(QUEUE,true,defaultConsumer);

    }
}
