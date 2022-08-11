package com.learn.topic;

import com.learn.RabbitMQTypeEnum;
import com.learn.utils.RabbitConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * @Title ConsumerNormal
 * @Description TODO
 * @Author ZLT
 * @Date 2022/8/9 10:14
 * @Version 1.0
 */
public class ConsumerTopic3 {

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitConnectionUtils.createChannel();
        /**
         * 声明交换机
         * 1、交换机名称
         * 2、交换机类型
         */
        channel.exchangeDeclare(RabbitConnectionUtils.TOPIC_EXCHANGES_NAME, RabbitMQTypeEnum.TOPIC.getKey());
        /**
         * 声明一个队列
         */
        channel.queueDeclare(RabbitConnectionUtils.TOPIC_QUEUE_ALL,true,false,false,null);
        /**
         * 绑定交换机和队列
         * 1、队列名称
         * 2、交换机名称
         * 3、routingKey
         */
        channel.queueBind(RabbitConnectionUtils.TOPIC_QUEUE_ALL, RabbitConnectionUtils.TOPIC_EXCHANGES_NAME, "log.#");

        DeliverCallback deliverCallback = (consumerTag, delivar)->{
            System.out.println("消费者3消费的消息："+new String(delivar.getBody(),"utf-8"));
        };

        /**
         * 消费消息
         * 1、队列名称
         * 2、是否自动确认
         * 3、接收消息回调函数
         * 4、取消消息回调函数
         */
        channel.basicConsume(RabbitConnectionUtils.TOPIC_QUEUE_ALL, true, deliverCallback,consumerTag->{});
    }
}
