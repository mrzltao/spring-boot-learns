package com.learn.consumer;

import com.learn.constants.RabbitMqConstants;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Title TtlDelayConsumer
 * @Description 基于存活时间的延迟队列消费者
 * @Author ZLT
 * @Date 2022/8/10 15:14
 * @Version 1.0
 */
@Component
@Slf4j
public class TtlDelayConsumer {

    @RabbitListener(queues = RabbitMqConstants.DEAD_QUEUE)
    public void consumerDeadDelay(Message msg, Channel channel) throws Exception{
        String resultMsg = new String(msg.getBody(), "utf-8");
        log.info("消费时间：{} 消费到的消息：{}", LocalDateTime.now(), resultMsg);
    }
}
