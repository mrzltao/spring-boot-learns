package com.learn.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Title Knife4jSwaggerConfig
 * @Description 配置文件
 * @Author Ltter
 * @Date 2022/8/17 15:48
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
@EnableConfigurationProperties(Knife4jSwaggerProperties.class)
public class Knife4jSwaggerConfig {

    @Autowired
    private Knife4jSwaggerProperties knife4jSwaggerProperties;

    @Bean
    public Docket customKnife4jSwaggerDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(customApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.learn"))
                .paths(PathSelectors.any())
                //不显示错误的接口地址 basic-error-controller
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    private ApiInfo customApiInfo() {
        return new ApiInfoBuilder()
                .title(knife4jSwaggerProperties.getTitle())
                .version(knife4jSwaggerProperties.getVersion())
                .description(knife4jSwaggerProperties.getDescription())
                .contact(new Contact(knife4jSwaggerProperties.getContactName()
                        , knife4jSwaggerProperties.getContactUrl()
                        , knife4jSwaggerProperties.getContactEmail()))
                .license(knife4jSwaggerProperties.getLicense())
                .licenseUrl(knife4jSwaggerProperties.getLicenseUrl())
                .build();
    }
}
