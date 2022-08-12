package com.learn.config;

import com.learn.constants.RabbitMqConstants;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title ConfirmConfig
 * @Description 发布确认高级
 * @Author Ltter
 * @Date 2022/8/11 16:24
 * @Version 1.0
 */
@Configuration
public class ConfirmConfig {

    /**
     * 发布确认
     */
    @Bean("confirmExchange")
    public DirectExchange confirmExchange(){
        //发布确认
        //return new DirectExchange(RabbitMqConstants.CONFIRM_EXCHANGE);

        //备份
        Map<String, Object> argumentsMap = new HashMap<>();
        //设置备份交换机
        argumentsMap.put("alternate-exchange", RabbitMqConstants.BACKUP_EXCHANGE);
        return ExchangeBuilder.directExchange(RabbitMqConstants.CONFIRM_EXCHANGE).durable(true)
                .withArguments(argumentsMap).build();
    }
    @Bean("confirmQueue")
    public Queue confirmQueue(){
        return QueueBuilder.durable(RabbitMqConstants.CONFIRM_QUEUE).build();
    }
    @Bean
    public Binding confirmQueueBindingExchange(@Qualifier("confirmQueue")Queue confirmQueue, @Qualifier("confirmExchange")DirectExchange confirmExchange){
        return BindingBuilder.bind(confirmQueue).to(confirmExchange).with(RabbitMqConstants.CONFIRM_ROUTING_KEY);
    }

    /**
     * 备份交换机
     */
    @Bean("backupExchange")
    public FanoutExchange backupExchange(){
        return new FanoutExchange(RabbitMqConstants.BACKUP_EXCHANGE);
    }
    @Bean("backupQueue")
    public Queue backupQueue(){
        return QueueBuilder.durable(RabbitMqConstants.BACKUP_QUEUE).build();
    }
    @Bean("warnQueue")
    public Queue warnQueue(){
        return QueueBuilder.durable(RabbitMqConstants.WARN_QUEUE).build();
    }
    @Bean
    public Binding backupQueueBindingExchange(@Qualifier("backupQueue")Queue backupQueue
            , @Qualifier("backupExchange")FanoutExchange backupExchange){
        return BindingBuilder.bind(backupQueue).to(backupExchange);
    }
    @Bean
    public Binding warnQueueBindingExchange(@Qualifier("warnQueue")Queue warnQueue
            , @Qualifier("backupExchange")FanoutExchange backupExchange){
        return BindingBuilder.bind(warnQueue).to(backupExchange);
    }
}
