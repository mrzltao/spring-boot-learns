package com.learn.direct;

import com.learn.utils.RabbitMQTypeEnum;
import com.learn.utils.RabbitConnectionUtils;
import com.rabbitmq.client.Channel;

/**
 * @Title ProducerDirect
 * @Description Direct路由类型
 * @Author Ltter
 * @Date 2022/8/8 16:52
 * @Version 1.0
 */
public class ProducerDirect {

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitConnectionUtils.createChannel();
        /**
         * 声明交换机
         * 1、交换机名称
         * 2、交换机类型
         */
        channel.exchangeDeclare(RabbitConnectionUtils.DIRECT_EXCHANGES_NAME, RabbitMQTypeEnum.DIRECT.getKey());
        String msg = "发送的消息";
        /**
         * 发送消息
         * 1、交换机
         * 2、routingKey
         * 3、其它参数
         * 4、消息体
         */
        channel.basicPublish(RabbitConnectionUtils.DIRECT_EXCHANGES_NAME, "warn",null, msg.getBytes("utf-8"));
    }
}
