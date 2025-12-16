package com.but.rebloom.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                                "http://localhost:5500",
                                "http://localhost:3000",
                                "http://localhost:5173",
                                "http://localhost:8080",

                                "http://rebloom.co.kr",
                                "http://www.rebloom.co.kr",
                                "http://api.rebloom.co.kr",

                                "https://rebloom.co.kr",
                                "https://www.rebloom.co.kr",
                                "https://api.rebloom.co.kr"

                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowCredentials(true);
            }
        };
    }
}
