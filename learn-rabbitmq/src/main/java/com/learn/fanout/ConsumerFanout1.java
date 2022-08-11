package com.learn.fanout;

import com.learn.RabbitMQTypeEnum;
import com.learn.utils.RabbitConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * @Title ConsumerNormal
 * @Description TODO
 * @Author Ltter
 * @Date 2022/8/9 10:14
 * @Version 1.0
 */
public class ConsumerFanout1 {

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitConnectionUtils.createChannel();
        /**
         * 声明交换机
         * 1、交换机名称
         * 2、交换机类型
         */
        channel.exchangeDeclare(RabbitConnectionUtils.FANOUT_EXCHANGES_NAME, RabbitMQTypeEnum.FANOUT.getKey());
        /**
         * 获取一个临时队列名称
         * 当断开连接时，临时队列就会被删除
         */
        String tempQueueName = channel.queueDeclare().getQueue();
        /**
         * 绑定交换机和队列
         * 1、队列名称
         * 2、交换机名称
         * 3、routingKey
         */
        channel.queueBind(tempQueueName, RabbitConnectionUtils.FANOUT_EXCHANGES_NAME, "");

        DeliverCallback deliverCallback = (consumerTag, delivar)->{
            System.out.println("消费者1消费的消息："+new String(delivar.getBody(),"utf-8"));
        };

        /**
         * 消费消息
         * 1、队列名称
         * 2、是否自动确认
         * 3、接收消息回调函数
         * 4、取消消息回调函数
         */
        channel.basicConsume(tempQueueName, true, deliverCallback,consumerTag->{});
    }
}
