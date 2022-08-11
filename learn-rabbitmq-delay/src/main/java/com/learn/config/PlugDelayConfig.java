package com.learn.config;

import com.learn.constants.RabbitMqConstants;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title PlugDelayConfig
 * @Description 基于插件的延迟队列
 * @Author Ltter
 * @Date 2022/8/10 15:12
 * @Version 1.0
 */
@Configuration
public class PlugDelayConfig {

    @Bean("delayExchange")
    public CustomExchange delayExchange(){
        Map<String,Object> argumentsMap = new HashMap<>();
        //延迟交换机类型
        argumentsMap.put("x-delayed-type", "direct");
        /**
         * 构造参数说明
         * 1、交换机名称
         * 2、交换机类型
         * 3、是否持久化
         * 4、是否自动删除
         * 5、其它参数
         */
        return new CustomExchange(RabbitMqConstants.DELAY_EXCHANGE, "x-delayed-message", true, false, argumentsMap);
    }

    @Bean("delayQueue")
    public Queue delayQueue(){
        return QueueBuilder.durable(RabbitMqConstants.DELAY_QUEUE).build();
    }

    @Bean
    public Binding delayQueueBindingExchange(@Qualifier("delayQueue") Queue delayQueue, @Qualifier("delayExchange") CustomExchange delayExchange){
        return BindingBuilder.bind(delayQueue).to(delayExchange).with(RabbitMqConstants.DELAY_ROUTING_KEY).noargs();
    }
}
