package com.learn;

import com.learn.utils.RabbitConnectionUtils;
import com.rabbitmq.client.*;

/**
 * @Title ConsumerRabbit
 * @Description 消费者
 * @Author Ltter
 * @Date 2022/8/4 14:50
 * @Version 1.0
 */
public class ConsumerRabbit {


    public static void main(String[] args) throws Exception {
        Channel channel = RabbitConnectionUtils.createChannel();
        /**
         * 接收消息的回调
         * String var1:消费者注册到RabbitMQ之后，RabbitMQ给生成的一个该消费者的唯一标识
         * Delivery var2：推送过来的消息；其中包括真正的数据body(消息体)，Properties(消息的属性信息)和其它信息
         */
        DeliverCallback deliverCallback = (String consumerTag, Delivery delivery)->{
            String message = new String(delivery.getBody(),"UTF-8");
            System.out.println(message);
            /**
             * 手动应答
             * boolean multiple:是否批量应答。false不批量应答。true批量应答。
             */
            //channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };

        /**
         * 取消消息的回调
         * String var1:消费者注册到RabbitMQ之后，RabbitMQ给生成的一个该消费者的唯一标识
         */
        CancelCallback cancelCallback = (String consumerTag)->{
            System.out.println("取消消费消息"+ consumerTag);
        };

        /**
         * 消费消息
         * 参数：
         *  1、消费的队列
         *  2、消费成功后是否自动应答；true自动应答；false手动应答
         *  3、消费者消费消息的回调
         *  4、消费者取消消费的回调
         */
        channel.basicConsume(RabbitConnectionUtils.QUEUE_NAME, true, deliverCallback, cancelCallback);
        //手动应答
        //channel.basicConsume(RabbitConnectionUtils.QUEUE_NAME, false, deliverCallback, cancelCallback);
    }
}
