package com.but.rebloom.domain.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Key;

@RequiredArgsConstructor
public class JwtUtil {

    protected final Key key;
    protected final Long expiration;

    public JwtUtil(String secretKey, Long expiration) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.expiration = expiration;
    }

    public String getUserEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        if (authentication != null) {
            // 이상하지만 안에 이메일 있음
            System.out.println(authentication.getName());
            return authentication.getName();
        }
        return null;
    }
}