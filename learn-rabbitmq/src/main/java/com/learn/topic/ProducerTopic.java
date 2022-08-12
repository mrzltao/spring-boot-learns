package com.learn.topic;

import com.learn.utils.RabbitMQTypeEnum;
import com.learn.utils.RabbitConnectionUtils;
import com.rabbitmq.client.Channel;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title ProducerDirect
 * @Description TODO
 * @Author Ltter
 * @Date 2022/8/8 16:52
 * @Version 1.0
 */
public class ProducerTopic {

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitConnectionUtils.createChannel();
        /**
         * 声明交换机
         * 1、交换机名称
         * 2、交换机类型
         */
        channel.exchangeDeclare(RabbitConnectionUtils.TOPIC_EXCHANGES_NAME, RabbitMQTypeEnum.TOPIC.getKey());
        Map<String,String> routingKeyMap = new HashMap<>();
        routingKeyMap.put(RabbitConnectionUtils.TOPIC_ROUTINGKEY_INFO_USR, "用户日志信息");
        routingKeyMap.put(RabbitConnectionUtils.TOPIC_ROUTINGKEY_INFO_LOGIN, "登录日志信息");
        routingKeyMap.put(RabbitConnectionUtils.TOPIC_ROUTINGKEY_INFO_OPERATION, "操作日志信息");
        routingKeyMap.put(RabbitConnectionUtils.TOPIC_ROUTINGKEY_WARN_USR, "用户日志警告信息");
        routingKeyMap.put(RabbitConnectionUtils.TOPIC_ROUTINGKEY_WARN_LOGIN, "登录日志警告信息");
        routingKeyMap.put(RabbitConnectionUtils.TOPIC_ROUTINGKEY_WARN_OPERATION, "操作日志警告信息");
        routingKeyMap.put(RabbitConnectionUtils.TOPIC_ROUTINGKEY_ERROR_USR, "用户错误日志信息");
        routingKeyMap.put(RabbitConnectionUtils.TOPIC_ROUTINGKEY_ERROR_LOGIN, "登录错误日志信息");
        routingKeyMap.put(RabbitConnectionUtils.TOPIC_ROUTINGKEY_ERROR_OPERATION, "操作错误日志信息");
        for (Map.Entry<String, String> routingKey : routingKeyMap.entrySet()) {
            /**
             * 发送消息
             * 1、交换机
             * 2、routingKey
             * 3、其它参数
             * 4、消息体
             */
            channel.basicPublish(RabbitConnectionUtils.TOPIC_EXCHANGES_NAME, routingKey.getKey(),null, routingKey.getValue().getBytes("utf-8"));
        }
        System.out.println("over。。。。。。。。。。。。");
    }
}
