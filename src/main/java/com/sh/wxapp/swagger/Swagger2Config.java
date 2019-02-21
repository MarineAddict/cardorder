package com.sh.wxapp.swagger;

import org.springframework.beans.factory.annotation.Value;
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

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Value("${swagger2.base.url}")
    private String swagger2Url;
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(swagger2Url)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.sh.wxapp.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    /**
     *@Description 构建 api文档的详细信息函数
     *@methodName apiInfo
     *@param
     *@return springfox.documentation.service.ApiInfo
     *@author hui.chen
     *@date 2018/10/25 14:58
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("carorder")
                //创建人
                .contact(new Contact("carorder", "", "492532570@qq.com"))
                //版本号
                .version("1.0")
                //描述
                .description("carorder")
                .build();
    }
}
