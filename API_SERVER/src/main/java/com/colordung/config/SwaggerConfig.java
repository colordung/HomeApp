package com.colordung.config;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        Contact contact = new Contact("t5online",
                "http://www.t5online.com",
                "swogjs@t5online.com");
        ApiInfo info = new ApiInfo(
                "Swagger for spring boot",
                "This documents describes about swagger for spring boot",
                "v1",
                "",
                contact,
                "MIT",
                "https://opensource.org/licenses/MIT",
                new ArrayList<>());

        ArrayList<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder()
                .code(500)
                .message("500 message")
                .build());

        responseMessages.add(new ResponseMessageBuilder()
                .code(404)
                .message("404 message")
                .build());

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
//				.globalResponseMessage(RequestMethod.GET, responseMessages)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .apiInfo(info)
//				.securitySchemes(Arrays.asList(apiKey()))
                ;
    }

//    private ApiKey apiKey() {
//        return new ApiKey("authkey", "jwt-header", "header");
//    }


}

