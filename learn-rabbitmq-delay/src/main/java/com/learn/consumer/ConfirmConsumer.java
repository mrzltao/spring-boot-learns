package com.learn.consumer;

import com.learn.constants.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Title ConfirmConsumer
 * @Description 发布确认高级  消费者
 * @Author Ltter
 * @Date 2022/8/11 16:40
 * @Version 1.0
 */
@Component
@Slf4j
public class ConfirmConsumer {

    @RabbitListener(queues = RabbitMqConstants.CONFIRM_QUEUE)
    public void confirmConsumer(Message message) throws Exception{
        String msg = new String(message.getBody(), "utf-8");
        log.info("消费到的信息为：{}", msg);
    }
}
