package com.learn.controller;

import com.learn.constants.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title ConfirmController
 * @Description 发布确认高级
 * @Author Ltter
 * @Date 2022/8/11 16:30
 * @Version 1.0
 */
@RestController
@RequestMapping("/confirm")
@Slf4j
public class ConfirmController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send/{msg}")
    public void send(@PathVariable String msg){
        /**
         * CorrelationData有俩个属性：id、ReturnedMessage
         */
        CorrelationData correlationData = new CorrelationData("confirm1");
        /**
         * correlationData只有在生产者中定义、并附上了id值，在交换机的回调函数中才能获取到correlationData的值，否则、回调函数correlationData为null。
         * 故回调函数中要做correlationData值的判空操作。
         */
        rabbitTemplate.convertAndSend(RabbitMqConstants.CONFIRM_EXCHANGE,RabbitMqConstants.CONFIRM_ROUTING_KEY, msg, correlationData);
        log.info("成功——发布确认高级——生产的消息为：{}", msg);
        rabbitTemplate.convertAndSend(RabbitMqConstants.CONFIRM_EXCHANGE + "1",RabbitMqConstants.CONFIRM_ROUTING_KEY, msg+"-交换机接收失败", correlationData);
        log.info("交换机接收失败—警报？—发布确认高级——生产的消息为：{}", msg+"-交换机接收失败");
        rabbitTemplate.convertAndSend(RabbitMqConstants.CONFIRM_EXCHANGE,RabbitMqConstants.CONFIRM_ROUTING_KEY + "1", msg+"-队列接收失败", correlationData);
        log.info("队列接收失败—警报—发布确认高级——生产的消息为：{}", msg+"-队列接收失败");
    }
}
