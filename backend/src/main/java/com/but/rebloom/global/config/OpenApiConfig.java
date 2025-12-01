package com.but.rebloom.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        servers = {
                @Server(
                        url = "https://api.rebloom.co.kr", // 운영 환경 배포용 URL
                        description = "Production Server"
                ),
                @Server(
                        url = "http://localhost:8080",
                        description = "Local Backend Server"
                )
        }
)
public class OpenApiConfig {
}