package com.learn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @Title SwaggerConfig
 * @Description Swagger配置文件
 * @Author Ltter
 * @Date 2022/8/17 9:26
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private SwaggerProperties swaggerProperties;

    /**
     * 配置Docket的Bean
     * @return
     */
    @Bean
    public Docket customDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                //接口文档的基础信息
                .apiInfo(customApiInfo())
                //对Api进行安全认证
                //.securitySchemes(customSecurity())
                //设置需要使用参数的接口
                //.securityContexts(customSecurityContext())
                .select()
                //1、扫描所有
                //.apis(RequestHandlerSelectors.any())
                //2、扫描所有使用注解的API，这种方法更灵活
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //3、扫描指定包中的注解
                .apis(RequestHandlerSelectors.basePackage("com.learn"))
                //路径风格
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 接口文档的基础信息
     * @return
     */
    private ApiInfo customApiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .version(swaggerProperties.getVersion())
                .description(swaggerProperties.getDescription())
                .contact(new Contact(swaggerProperties.getContactName(), swaggerProperties.getContactUrl(), swaggerProperties.getContactEmail()))
                .license(swaggerProperties.getLicense())
                .licenseUrl(swaggerProperties.getLicenseUrl())
                .build();
    }

    /**
     * 对Api进行安全认证
     * @return
     */
    private List<ApiKey> customSecurity() {
        return null;
    }

    /**
     * 设置需要使用参数的接口
     * @return
     */
    private List<SecurityContext> customSecurityContext() {
        return null;
    }
}
