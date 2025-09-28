package com.but.rebloom.auth.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Key;

public class JwtUtil {

    protected final Key key;
    protected final Long expiration;

    public JwtUtil(String secretKey, Long expiration) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.expiration = expiration;
    }

    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            // 이상하지만 안에 이메일 있음
            return authentication.getName();
        }
        return null;
    }
}
