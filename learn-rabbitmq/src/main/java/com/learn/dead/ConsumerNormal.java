package com.learn.dead;

import com.learn.utils.RabbitMQTypeEnum;
import com.learn.utils.RabbitConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title ConsumerNormal
 * @Description TODO
 * @Author ltter
 * @Date 2022/8/9 10:14
 * @Version 1.0
 */
public class ConsumerNormal {

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitConnectionUtils.createChannel();
        /**
         * 普通交换机和普通队列
         */
        channel.exchangeDeclare(RabbitConnectionUtils.NOMAL_EXCHANGE_NAME, RabbitMQTypeEnum.DIRECT.getKey());
        /**
         * 声明队列时，需要设置一些参数才能将正常队列与死信交换机做关联
         * 以便于消息成为死信之后，将死信消息传递到死信交换机
         */
        Map<String, Object> map = new HashMap<>();
        /**
         * 将正常队列设置为死信队列
         */
        map.put("x-dead-letter-exchange", RabbitConnectionUtils.DEAD_EXCHANGE_NAME);
        /**
         * 设置死信队列的routing-key
         */
        map.put("x-dead-letter-routing-key", RabbitConnectionUtils.DEAD_ROUTING);
        /**
         * 设置正常队列长度限制
         */
        //map.put("x-max-length", 6);
        channel.queueDeclare(RabbitConnectionUtils.NOMAL_QUEUE, false, false, false, map);
        channel.queueBind(RabbitConnectionUtils.NOMAL_QUEUE, RabbitConnectionUtils.NOMAL_EXCHANGE_NAME, RabbitConnectionUtils.NOMAL_ROUTINGKEY);
        /**
         * 死信交换机和死信队列
         */
        channel.exchangeDeclare(RabbitConnectionUtils.DEAD_EXCHANGE_NAME, RabbitMQTypeEnum.DIRECT.getKey());
        channel.queueDeclare(RabbitConnectionUtils.DEAD_QUEUE, false, false, false, null);
        channel.queueBind(RabbitConnectionUtils.DEAD_QUEUE, RabbitConnectionUtils.DEAD_EXCHANGE_NAME, RabbitConnectionUtils.DEAD_ROUTING);
        DeliverCallback deliverCallback = (consumerTag, msg)->{
            //System.out.println("消费者1消费的普通消息："+ new String(msg.getBody(), "utf-8"));
            //消息被拒绝成为死信     全部拒绝
            channel.basicReject(msg.getEnvelope().getDeliveryTag(), false);
        };
        //channel.basicConsume(RabbitConnectionUtils.NOMAL_QUEUE, true, deliverCallback, consumerTag->{});
        //开启手动应答
        channel.basicConsume(RabbitConnectionUtils.NOMAL_QUEUE, false, deliverCallback, consumerTag->{});
    }
}
