package com.learn.controller;

import com.learn.constants.RabbitMqConstants;
import com.rabbitmq.client.MessageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title PriorityController
 * @Description 优先级队列示例
 * @Author Ltter
 * @Date 2022/8/12 17:07
 * @Version 1.0
 */
@RestController
@RequestMapping("/priority")
@Slf4j
public class PriorityController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendPriMsg/{msg}")
    public void sendPriMsg(@PathVariable String msg){
        log.info("发送消息到优先级队列");
        for (int i = 1; i <= 10; i++) {
            if (i == 6){
                rabbitTemplate.convertAndSend(RabbitMqConstants.PRIORITY_EXCHAGNE, RabbitMqConstants.PRIORITY_ROUTING_KEY, "消息"+String.valueOf(i), message->{
                    /**
                     * 设置消息的优先级
                     * 当没有设置消息的优先级时，该消息的优先级最低。
                     */
                    message.getMessageProperties().setPriority(5);
                    return message;
                });
            }else if (i == 9){
                rabbitTemplate.convertAndSend(RabbitMqConstants.PRIORITY_EXCHAGNE, RabbitMqConstants.PRIORITY_ROUTING_KEY, "消息"+String.valueOf(i), message->{
                    /**
                     * 设置消息的优先级
                     * 当没有设置消息的优先级时，该消息的优先级最低。
                     */
                    message.getMessageProperties().setPriority(3);
                    return message;
                });
            }else {
                rabbitTemplate.convertAndSend(RabbitMqConstants.PRIORITY_EXCHAGNE, RabbitMqConstants.PRIORITY_ROUTING_KEY, "消息"+String.valueOf(i));
            }
        }
    }
}
