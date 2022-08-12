package com.learn.config;

import com.learn.constants.RabbitMqConstants;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title PriorityConfig
 * @Description 优先级队列
 * @Author ZLT
 * @Date 2022/8/12 17:00
 * @Version 1.0
 */
@Configuration
public class PriorityConfig {

    @Bean("priorityExchagne")
    public DirectExchange priorityExchagne(){
        return new DirectExchange(RabbitMqConstants.PRIORITY_EXCHAGNE);
    }
    @Bean("priorityQueue")
    public Queue priorityQueue(){
        Map<String,Object> arguments = new HashMap<>();
        //设置最大优先级
        arguments.put("x-max-priority", 10);
        //设置优先级队列
        return QueueBuilder.durable(RabbitMqConstants.PRIORITY_QUEUE).withArguments(arguments).build();
    }
    @Bean
    public Binding priorityQueueBindingExchange(@Qualifier("priorityQueue")Queue priorityQueue
            , @Qualifier("priorityExchagne")DirectExchange priorityExchagne){
        return BindingBuilder.bind(priorityQueue).to(priorityExchagne).with(RabbitMqConstants.PRIORITY_ROUTING_KEY);
    }
}
