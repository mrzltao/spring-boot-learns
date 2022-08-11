package com.learn.controller;

import com.learn.constants.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @Title DelayController
 * @Description TODO
 * @Author ZLT
 * @Date 2022/8/10 15:08
 * @Version 1.0
 */
@RestController
@RequestMapping("/delay")
@Slf4j
public class DelayController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg/{msg}/{timeout}")
    public void sendMsg(@PathVariable String msg, @PathVariable String timeout){
        log.info("发送时间：{}  存活时间：{} MS  发送消息：{}", LocalDateTime.now(), timeout, msg);
        rabbitTemplate.convertAndSend(RabbitMqConstants.NOMAL_EXCHANGE, RabbitMqConstants.NOMAL_ROUTING_KEY, msg, argumentsMsg -> {
            argumentsMsg.getMessageProperties().setExpiration(timeout);
            return argumentsMsg;
        });
    }
}
