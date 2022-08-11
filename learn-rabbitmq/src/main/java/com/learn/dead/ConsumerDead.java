package com.learn.dead;

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
public class ConsumerDead {

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitConnectionUtils.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivar)->{
            System.out.println("消费者2消费的死信消息："+new String(delivar.getBody(),"utf-8"));
        };

        /**
         * 消费消息
         * 1、队列名称
         * 2、是否自动确认
         * 3、接收消息回调函数
         * 4、取消消息回调函数
         */
        channel.basicConsume(RabbitConnectionUtils.DEAD_QUEUE, true, deliverCallback, consumerTag->{});
    }
}
