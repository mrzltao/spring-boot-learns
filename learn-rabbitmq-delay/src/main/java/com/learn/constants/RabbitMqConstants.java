package com.learn.constants;

/**
 * @Title RabbitMqConstants
 * @Description 常量
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
    public static final String BACKUP_EXCHANGE = "backup.exchange";
    public static final String PRIORITY_EXCHAGNE = "priority.exchange";
    /**
     * 队列
     */
    public static final String NOMAL_QUEUE = "NQ";
    public static final String DEAD_QUEUE = "DQ";
    public static final String DELAY_QUEUE = "delay.queue";
    public static final String CONFIRM_QUEUE = "confirm.queue";
    public static final String BACKUP_QUEUE = "backup.queue";
    public static final String WARN_QUEUE = "warn.queue";
    public static final String PRIORITY_QUEUE = "priority.queue";
    /**
     * routing-key
     */
    public static final String NOMAL_ROUTING_KEY = "NRT";
    public static final String DEAD_ROUTING_KEY = "DRT";
    public static final String DELAY_ROUTING_KEY = "delay.routing.key";
    public static final String CONFIRM_ROUTING_KEY = "confirm.routing.key";
    public static final String PRIORITY_ROUTING_KEY = "priority.routing.key";

}
