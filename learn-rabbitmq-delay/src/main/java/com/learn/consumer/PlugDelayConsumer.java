package com.learn.consumer;

import com.learn.constants.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.lang.invoke.ConstantCallSite;
import java.time.LocalDateTime;

/**
 * @Title PlugDelayConsumer
 * @Description 基于插件延迟队列的消费者
 * @Author Ltter
 * @Date 2022/8/10 15:15
 * @Version 1.0
 */
@Component
@Slf4j
public class PlugDelayConsumer {

    @RabbitListener(queues = RabbitMqConstants.DELAY_QUEUE)
    public void delayConsumer(Message message) throws Exception{
        String resultMsg = new String(message.getBody(), "utf-8");
        log.info("消费时间：{} 消费到的消息：{}", LocalDateTime.now(), resultMsg);
    }
}
