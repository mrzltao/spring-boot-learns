package com.learn.consumer;

import com.learn.constants.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Title PriorityConsumer
 * @Description 优先级队列消费者
 * @Author ZLT
 * @Date 2022/8/12 17:15
 * @Version 1.0
 */
@Component
@Slf4j
public class PriorityConsumer {

    @RabbitListener(queues = RabbitMqConstants.PRIORITY_QUEUE)
    public void priorityCon(Message message) throws Exception{
        String msg = new String(message.getBody(), "utf-8");
        log.info("消费：{}", msg);
    }
}
