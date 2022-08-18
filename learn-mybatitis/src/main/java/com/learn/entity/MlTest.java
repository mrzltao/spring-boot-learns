package com.learn.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title MlTest
 * @Description MlTest
 * @Author Ltter
 * @Date 2022/8/18 15:00
 * @Version 1.0
 */
@Data
public class MlTest implements Serializable {

    private String id;

    /**
     * 解决命名规范问题；像这种字段在get/set时，TN都是大写的字母挨在了一起，所以无法赋值。这时需要用到@JsonProperty注解解决
     */
    @JsonProperty(value = "tName")
    private String tName;

    private Integer age;

    private Date birthday;
}
