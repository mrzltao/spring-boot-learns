package com.learn.consumer;

import com.learn.constants.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Title warnConsumer
 * @Description 备份交换机——警报队列——报警消费者
 * @Author ZLT
 * @Date 2022/8/12 15:22
 * @Version 1.0
 */
@Component
@Slf4j
public class WarnConsumer {

    @RabbitListener(queues = RabbitMqConstants.WARN_QUEUE)
    public void warningCons(Message message)throws Exception{
        String msg = new String(message.getBody(), "utf-8");
        log.info("报警消息：{}", msg);
    }
}
