package com.learn.confirm;

import com.learn.utils.RabbitConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.MessageProperties;

import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @Title ProducerConfirm
 * @Description TODO
 * @Author Ltter
 * @Date 2022/8/8 15:03
 * @Version 1.0
 */
public class ProducerConfirm {

    public static final Integer QUEUE_MSG = 100;

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
        //开启发布确认
        channel.confirmSelect();
        //单个发布确认
        //confirmSingle(channel);
        //批量发布确认
        //confirmBatch(channel);
        //异步发布确认
        confirmAsync(channel);
        //处理异步未确认消息
        notConfirmAsync(channel);
        System.out.println("over................");
    }

    private static void notConfirmAsync(Channel channel) throws Exception {
        /**
         * 适用于高并发情况下，且线程安全、有序的哈希表
         */
        ConcurrentSkipListMap<Long, String> publishMsgMap = new ConcurrentSkipListMap<>();

        //ConcurrentLinkedQueue<>
        /**
         * deliveryTag：消息编号
         * multiple：是否为批量确认
         */
        ConfirmCallback ackCallback = (long deliveryTag, boolean multiple)->{
            //成功
            if (multiple){//是否批量
                //2、删除成功的消息
                ConcurrentNavigableMap<Long, String> successConfirmMap = publishMsgMap.headMap(deliveryTag);
                successConfirmMap.clear();
            }else {
                publishMsgMap.remove(deliveryTag);
            }
        };
        /**
         * deliveryTag：消息编号
         * multiple：是否为批量确认
         */
        ConfirmCallback nackCallback = (long deliveryTag, boolean multiple)->{
            //失败
            //3、未确认的消息
            publishMsgMap.get(deliveryTag);
        };

        //异步发布确认    消息监听器
        channel.addConfirmListener(ackCallback, nackCallback);

        for (Integer i = 0; i < QUEUE_MSG; i++) {
            String queueMsg = "消息"+QUEUE_MSG.toString();
            channel.basicPublish("", RabbitConnectionUtils.QUEUE_NAME, null, queueMsg.getBytes());
            //1、记录所有发布的消息
            publishMsgMap.put(channel.getNextPublishSeqNo(), queueMsg);
        }
    }

    private static void confirmAsync(Channel channel) throws Exception{
        /**
         * deliveryTag：消息编号
         * multiple：是否为批量确认
         */
        ConfirmCallback ackCallback = (long deliveryTag, boolean multiple)->{
            //成功
        };
        /**
         * deliveryTag：消息编号
         * multiple：是否为批量确认
         */
        ConfirmCallback nackCallback = (long deliveryTag, boolean multiple)->{
            //失败
        };

        //异步发布确认    消息监听器
        //channel.addConfirmListener(ConfirmListener);
        channel.addConfirmListener(ackCallback, nackCallback);

        for (Integer i = 0; i < QUEUE_MSG; i++) {
            String queueMsg = "消息"+QUEUE_MSG.toString();
            channel.basicPublish("", RabbitConnectionUtils.QUEUE_NAME, null, queueMsg.getBytes());
        }
    }

    private static void confirmBatch(Channel channel) throws Exception {
        for (Integer i = 0; i < QUEUE_MSG; i++) {
            String queueMsg = "消息"+QUEUE_MSG.toString();
            channel.basicPublish("", RabbitConnectionUtils.QUEUE_NAME, null, queueMsg.getBytes());
        }
        //发布确认
        boolean flag = channel.waitForConfirms();
        if (flag){
            System.out.println("SUCCESS");
        }
    }

    private static void confirmSingle(Channel channel) throws Exception {
        for (Integer i = 0; i < QUEUE_MSG; i++) {
            String queueMsg = "消息"+QUEUE_MSG.toString();
            channel.basicPublish("", RabbitConnectionUtils.QUEUE_NAME, null, queueMsg.getBytes());
            //发布确认
            boolean flag = channel.waitForConfirms();
            if (flag){
                System.out.println("SUCCESS");
            }
        }
    }
}
