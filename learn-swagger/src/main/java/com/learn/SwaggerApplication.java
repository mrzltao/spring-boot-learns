package com.learn;

import com.learn.config.SwaggerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @Title SwaggerApplication
 * @Description swagger示例
 * @Author Ltter
 * @Date 2022/8/17 9:19
 * @Version 1.0
 */
@SpringBootApplication
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class);
    }
}
