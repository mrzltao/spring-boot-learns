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
     * 队列名称
     */
    public static final String QUEUE_NAME = "TEST_RABBIT_ONE";

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
