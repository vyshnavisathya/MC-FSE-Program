package com.cognizant.customer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private String API_NAME = "Customer Details API";
    private String PACKAGE_SCAN = "com.cognizant.microcredential.program.cardpaymentcustomerservice.customer.controller";

    /*@Bean
    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2).groupName(API_NAME).select()
                .apis(RequestHandlerSelectors.basePackage(PACKAGE_SCAN)).paths(PathSelectors.any()).build();

    }*/
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}


