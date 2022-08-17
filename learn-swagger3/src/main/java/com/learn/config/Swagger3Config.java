package com.learn.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @Title Swagger3Config
 * @Description Swagger3配置文件
 * @Author Ltter
 * @Date 2022/8/17 14:59
 * @Version 1.0
 */

@Configuration
@EnableSwagger2
@EnableConfigurationProperties(Swagger3Properties.class)
public class Swagger3Config {

    @Autowired
    private Swagger3Properties swagger3Properties;

    @Bean
    public Docket customSwagger3Docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(customApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.learn"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo customApiInfo() {
        return new ApiInfoBuilder()
                .title(swagger3Properties.getTitle())
                .version(swagger3Properties.getVersion())
                .description(swagger3Properties.getDescription())
                .contact(new Contact(swagger3Properties.getContactName()
                        , swagger3Properties.getContactUrl()
                        , swagger3Properties.getContactEmail()))
                .license(swagger3Properties.getLicense())
                .licenseUrl(swagger3Properties.getLicenseUrl())
                .build();
    }

}
