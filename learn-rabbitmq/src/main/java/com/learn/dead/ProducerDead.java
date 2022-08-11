package com.learn.dead;

import com.learn.RabbitMQTypeEnum;
import com.learn.utils.RabbitConnectionUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

/**
 * @Title ProducerDead
 * @Description TODO
 * @Author ZLT
 * @Date 2022/8/10 10:12
 * @Version 1.0
 */
public class ProducerDead {
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitConnectionUtils.createChannel();
        /**
         * 声明交换机
         * 1、交换机名称
         * 2、交换机类型
         */
        channel.exchangeDeclare(RabbitConnectionUtils.NOMAL_EXCHANGE_NAME, RabbitMQTypeEnum.DIRECT.getKey());
        /**
         * 设置消息过期时间（毫秒）——死信消息
         */
        //AMQP.BasicProperties timeout = new AMQP.BasicProperties().builder().expiration("10000").build();

        for (int i = 0; i < 10; i++) {
            String msg = "发送的消息" + i;
            /**
             * 发送消息
             * 1、交换机
             * 2、routingKey
             * 3、其它参数
             * 4、消息体
             */
            //channel.basicPublish(RabbitConnectionUtils.NOMAL_EXCHANGE_NAME, RabbitConnectionUtils.NOMAL_ROUTINGKEY, timeout, msg.getBytes("utf-8"));
            channel.basicPublish(RabbitConnectionUtils.NOMAL_EXCHANGE_NAME, RabbitConnectionUtils.NOMAL_ROUTINGKEY, null, msg.getBytes("utf-8"));
            Thread.sleep(1000);
        }
    }
}
