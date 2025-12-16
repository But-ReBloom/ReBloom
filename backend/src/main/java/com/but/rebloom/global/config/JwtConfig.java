package com.but.rebloom.global.config;

import com.but.rebloom.domain.auth.jwt.JwtAuthenticationFilter;
import com.but.rebloom.domain.auth.jwt.JwtFilter;
import com.but.rebloom.domain.auth.jwt.JwtTokenProvider;
import com.but.rebloom.domain.auth.jwt.JwtUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtConfig {

    private String secret;
    private Long expiration;

    // DI
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

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterRegistration(JwtFilter filter) {
        FilterRegistrationBean<JwtFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false); // 필터의 자동 등록을 방지
        return registration;
    }
}
