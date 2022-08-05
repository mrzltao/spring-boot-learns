package com.learn;

import com.learn.utils.RabbitConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Title ProducerRabbit
 * @Description TODO
 * @Author ZLT
 * @Date 2022/8/4 13:55
 * @Version 1.0
 */
public class ProducerRabbit {

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitConnectionUtils.createChannel();
        /**
         * queueDeclare 创建队列
         * 1、队列名称
         * 2、队列中的消息是否持久化（磁盘），默认false消息存储在内存中。true持久化（存储在磁盘上）
         * 3、队列是否只供一个消费者进行消费。是否进行消息共享。true可以多个消费者消费。false只能一个消费者消费
         * 4、是否自动删除，最后一个消费者断开连接后，本队列是否自动删除。true 自动删除。false 不自动删除
         * 5、其它参数
         */
        channel.queueDeclare(RabbitConnectionUtils.QUEUE_NAME, true, false ,false,null);
        String queueMsg = "发送的一条队列消息。。。。。。。one";

        /**
         * 发送消息
         * 1、交换机：发送到那个交换机
         * 2、路由key:路由的key值，本次为队列名称
         * 3、其它参数
         * 4、消息体
         */
        channel.basicPublish("", RabbitConnectionUtils.QUEUE_NAME, null, queueMsg.getBytes());
        System.out.println("over................");
    }
}
