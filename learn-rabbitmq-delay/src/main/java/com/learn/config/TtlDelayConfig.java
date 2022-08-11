package com.learn.config;

import com.learn.constants.RabbitMqConstants;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title TtlDelayConfig
 * @Description 基于过期时间死信队列的延迟队列
 * @Author Ltter
 * @Date 2022/8/10 15:08
 * @Version 1.0
 */
@Configuration
public class TtlDelayConfig {

    /**
     * 声明交换机
     * @return
     */
    @Bean("exchangeN")
    public DirectExchange exchangeN(){
        return new DirectExchange(RabbitMqConstants.NOMAL_EXCHANGE);
    }
    @Bean("exchangeD")
    public DirectExchange exchangeD(){
        return new DirectExchange(RabbitMqConstants.DEAD_EXCHANGE);
    }
    /**
     * 声明队列
     */
    @Bean("queueN")
    public Queue queueN(){
        Map<String,Object> argumentsMap = new HashMap<>(2);
        //设置死信交换机
        argumentsMap.put("x-dead-letter-exchange", RabbitMqConstants.DEAD_EXCHANGE);
        //设置死信队列的routing-key
        argumentsMap.put("x-dead-letter-routing-key", RabbitMqConstants.DEAD_ROUTING_KEY);
        /**
         * durable(name):持久化的队列名称
         * .withArguments(map):参数（多个）   .withArgument(key,value)：参数（一个）
         */
        return QueueBuilder.durable(RabbitMqConstants.NOMAL_QUEUE).withArguments(argumentsMap).build();
    }

    @Bean("queueD")
    public Queue queueD(){
        return QueueBuilder.durable(RabbitMqConstants.DEAD_QUEUE).build();
    }

    /**
     * 绑定交换机与队列
     */
    @Bean
    public Binding queueFBindingExchangeN(@Qualifier("queueN") Queue queueN, @Qualifier("exchangeN") DirectExchange exchangeN){
        return BindingBuilder.bind(queueN).to(exchangeN).with(RabbitMqConstants.NOMAL_ROUTING_KEY);
    }
    @Bean
    public Binding queueDBindingExchangeD(@Qualifier("queueD") Queue queueD, @Qualifier("exchangeD") DirectExchange exchangeD){
        return BindingBuilder.bind(queueD).to(exchangeD).with(RabbitMqConstants.DEAD_ROUTING_KEY);
    }
}
