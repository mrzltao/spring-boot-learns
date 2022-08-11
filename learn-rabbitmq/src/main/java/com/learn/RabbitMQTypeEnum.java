package com.learn;

import lombok.Getter;

/**
 * @Title RabbitMQTypeEnum
 * @Description TODO
 * @Author Ltter
 * @Date 2022/8/9 9:54
 * @Version 1.0
 */
@Getter
public enum RabbitMQTypeEnum {
    DIRECT("direct","路由类型"),
    TOPIC("topic","主题类型"),
    HEADERS("headers","标题类型"),
    FANOUT("fanout","扇出类型");

    private String key;

    private String value;

    RabbitMQTypeEnum(String key, String value){
        this.key = key;
        this.value = value;
    }
}
