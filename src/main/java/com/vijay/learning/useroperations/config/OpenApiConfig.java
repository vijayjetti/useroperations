package com.vijay.learning.useroperations.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI userOperationsOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Operations API")
                        .description("Spring Boot REST API for User Operations")
                        .version("1.0"));
    }
}