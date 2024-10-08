package com.danilomassoni.APISimple.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.ListenerNotFoundException;

@Configuration
public class OpenAPIConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Hello Swagger OpenAPI")
                .version("v1")
                .description("Simple API for persona.")
                .termsOfService("")
                .license(
                        new License()
                                .name("Apache 2.0")
                                .url("")));
    }
}
