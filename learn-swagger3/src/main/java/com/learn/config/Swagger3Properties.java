package com.learn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Title SwaggerProperties
 * @Description 属性
 * @Author Ltter
 * @Date 2022/8/17 14:01
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "swagger")
public class Swagger3Properties {

    /**
     * 作者名称
     */
    private String contactName;
    /**
     * 作者—url
     */
    private String contactUrl;
    /**
     * 作者邮箱
     */
    private String contactEmail;
    /**
     * 标题
     */
    private String title;
    /**
     * 版本
     */
    private String version;
    /**
     * 描述
     */
    private String description;
    /**
     *
     */
    private String termsOfServiceUrl;
    /**
     * 网站连接显示文字
     */
    private String license;
    /**
     * 网站连接地址
     */
    private String licenseUrl;
}
