package com.richard.config.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/***
 * @author: richard 
 * @date: 2018年9月7日 下午10:52:57
 * @version:
 * @Description: Swagger2 通用配置
 */
@Configuration
public class Swagger2Config {

    @Bean(name="mySwagger2Api")
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.richard.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RESTful APIs Doc")
                .description("文档简要描述！")
                .termsOfServiceUrl("www.baidu.com")
                .contact("Richard")
                .version("v1.0")
                .build();
    }

}