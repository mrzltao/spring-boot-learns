package com.learn.callback;

import com.learn.constants.RabbitMqConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Title ConfirmCallback
 * @Description 发布确认回调函数
 * @Author Ltter
 * @Date 2022/8/11 16:44
 * @Version 1.0
 */
@Configuration
@Slf4j
public class ConfirmCallback implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnsCallback {

    /**
     * 将回调实现类注入到RabbitTemplate
     */
    //===============注入开始======================================================
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 初始化
     */
    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }
    //===============注入结束======================================================

    /**
     * 交换机的回调函数
     * @param correlationData 有值与否，取决与生产者是否定义、赋值
     * @param ack 是否消费成功
     * @param cause 原因  消费成功则为null
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData == null? "": correlationData.getId();
        if (ack){
            log.info("交换机：{} 接收消息成功", RabbitMqConstants.CONFIRM_EXCHANGE);
        }else {
            log.info("交换机：{} 因为 {} 原因接收消息失败", RabbitMqConstants.CONFIRM_EXCHANGE, cause);
        }
    }

    /**
     * 队列的回调函数
     * @param returned
     * ReturnedMessage中的属性值有：
     *     Message message; 消息的信息
     *     int replyCode; 失败编码
     *     String replyText; 失败原因
     *     String exchange; 失败的交换机
     *     String routingKey; 失败的routing-key
     */
    @SneakyThrows
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.info("失败的交换机：{} 失败的routing-key：{} 消息：{} 编码：{} 原因：{}"
                , returned.getExchange()
                , returned.getRoutingKey()
                , new String(returned.getMessage().getBody(), "utf-8")
                , returned.getReplyCode()
                , returned.getReplyText());
    }
}
