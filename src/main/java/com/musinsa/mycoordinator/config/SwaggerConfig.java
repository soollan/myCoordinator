package com.musinsa.mycoordinator.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(servers = {@Server(url = "/")})
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
            .title("MUSINSA API")
            .version("v1");

        return new OpenAPI()
            .info(info);
    }
}
