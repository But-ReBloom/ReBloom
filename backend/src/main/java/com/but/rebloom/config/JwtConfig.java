package com.but.rebloom.config;

import com.but.rebloom.auth.jwt.JwtAuthenticationFilter;
import com.but.rebloom.auth.jwt.JwtFilter;
import com.but.rebloom.auth.jwt.JwtTokenProvider;
import com.but.rebloom.auth.jwt.JwtUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtConfig {

    private String secret;
    private Long expiration;

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil(secret, expiration);
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider(secret, expiration);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(secret, expiration);
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(jwtAuthenticationFilter());
    }
}
