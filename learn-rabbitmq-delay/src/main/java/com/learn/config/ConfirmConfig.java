package com.learn.config;

import com.learn.constants.RabbitMqConstants;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title ConfirmConfig
 * @Description 发布确认高级
 * @Author Ltter
 * @Date 2022/8/11 16:24
 * @Version 1.0
 */
@Configuration
public class ConfirmConfig {

    @Bean("confirmExchange")
    public DirectExchange confirmExchange(){
        return new DirectExchange(RabbitMqConstants.CONFIRM_EXCHANGE);
    }
    @Bean("confirmQueue")
    public Queue confirmQueue(){
        return QueueBuilder.durable(RabbitMqConstants.CONFIRM_QUEUE).build();
    }
    @Bean
    public Binding confirmQueueBindingExchange(@Qualifier("confirmQueue")Queue confirmQueue, @Qualifier("confirmExchange")DirectExchange confirmExchange){
        return BindingBuilder.bind(confirmQueue).to(confirmExchange).with(RabbitMqConstants.CONFIRM_ROUTING_KEY);
    }
}
