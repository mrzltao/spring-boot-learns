package com.learn.constants;

/**
 * @Title RabbitMqConstants
 * @Description TODO
 * @Author Ltter
 * @Date 2022/8/10 16:36
 * @Version 1.0
 */
public class RabbitMqConstants {

    /**
     * 交换机
     */
    public static final String NOMAL_EXCHANGE = "NE";
    public static final String DEAD_EXCHANGE = "DE";
    public static final String DELAY_EXCHANGE = "delay.exchagne";
    public static final String CONFIRM_EXCHANGE = "confirm.exchange";
    /**
     * 队列
     */
    public static final String NOMAL_QUEUE = "NQ";
    public static final String DEAD_QUEUE = "DQ";
    public static final String DELAY_QUEUE = "delay.queue";
    public static final String CONFIRM_QUEUE = "confirm.queue";
    /**
     * routing-key
     */
    public static final String NOMAL_ROUTING_KEY = "NRT";
    public static final String DEAD_ROUTING_KEY = "DRT";
    public static final String DELAY_ROUTING_KEY = "delay.routing.key";
    public static final String CONFIRM_ROUTING_KEY = "confirm.routing.key";

}
