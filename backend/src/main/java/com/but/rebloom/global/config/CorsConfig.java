package com.but.rebloom.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*")); // 모든 Origin 허용 (패턴 사용)
        configuration.setAllowedOrigins(Arrays.asList(
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
        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
