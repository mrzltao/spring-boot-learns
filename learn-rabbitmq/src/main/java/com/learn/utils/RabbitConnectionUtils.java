package com.learn.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Title RabbitConnectionUtils
 * @Description TODO
 * @Author ZLT
 * @Date 2022/8/4 13:55
 * @Version 1.0
 */
public class RabbitConnectionUtils {

    /**
     * 队列Queue
     */
    public static final String QUEUE_NAME = "TEST_RABBIT_ONE";
    public static final String DIRECT_QUEUE_INFO = "TEST_DIRECT_QUEUE_RABBIT_INFO";
    public static final String DIRECT_QUEUE_ERR = "TEST_DIRECT_QUEUE_RABBIT_ERR";
    public static final String TOPIC_QUEUE_INFO = "TEST_DTOPIC_QUEUE_INFO";
    public static final String TOPIC_QUEUE_ERROR = "TEST_TOPIC_QUEUE_ERROR";
    public static final String TOPIC_QUEUE_ALL= "TOPIC_QUEUE_ALL";
    public static final String NOMAL_QUEUE = "NOMAL_QUEUE";
    public static final String DEAD_QUEUE = "DEAD_QUEUE";

    /**
     * routingKey
     */
    public static final String TOPIC_ROUTINGKEY_INFO_USR = "log.info.usr";
    public static final String TOPIC_ROUTINGKEY_INFO_LOGIN = "log.info.login";
    public static final String TOPIC_ROUTINGKEY_INFO_OPERATION = "log.info.operation";
    public static final String TOPIC_ROUTINGKEY_WARN_USR = "log.warn.usr";
    public static final String TOPIC_ROUTINGKEY_WARN_LOGIN = "log.warn.login";
    public static final String TOPIC_ROUTINGKEY_WARN_OPERATION = "log.warn.operation";
    public static final String TOPIC_ROUTINGKEY_ERROR_USR = "log.error.usr";
    public static final String TOPIC_ROUTINGKEY_ERROR_LOGIN = "log.error.login";
    public static final String TOPIC_ROUTINGKEY_ERROR_OPERATION = "log.error.operation";
    public static final String NOMAL_ROUTINGKEY = "NOMAL_ROUTINGKEY";
    public static final String DEAD_ROUTING = "DEAD_ROUTING";

    /**
     * 交换机Exchanges
     */
    public static final String FANOUT_EXCHANGES_NAME = "TEST_FANOUT_EXCHANGES_NAME";
    public static final String DIRECT_EXCHANGES_NAME = "DIRECT_EXCHANGES_NAME";
    public static final String TOPIC_EXCHANGES_NAME = "TOPIC_EXCHANGES_NAME";
    public static final String NOMAL_EXCHANGE_NAME = "NOMAL_EXCHANGE_NAME";
    public static final String DEAD_EXCHANGE_NAME = "DEAD_EXCHANGE_NAME";

    public static Channel createChannel() throws Exception {
        //连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置连接地址
        factory.setHost("192.168.1.117");
        //设置端口
        factory.setPort(5672);
        //用户名
        factory.setUsername("zlt");
        //密码
        factory.setPassword("123");
        //设置虚拟库
        factory.setVirtualHost("/vhost_test");
        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        return channel;
    }
}
