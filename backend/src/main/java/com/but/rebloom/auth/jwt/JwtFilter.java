package com.but.rebloom.auth.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtAuthenticationFilter jwtAuth;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        if (path.equals("/favicon.ico") ||
                path.startsWith("/.well-known/") ||
                path.startsWith("/auth/") ||
                path.startsWith("/email/") ||
                path.startsWith("/last/") ||
                path.startsWith("/main/") ||
                path.startsWith("/img/") ||
                path.startsWith("/login/") ||
                path.startsWith("/logout/") ||
                path.startsWith("/inquiry/") ||
                path.startsWith("/room/") ||
                path.startsWith("/class/") ||
                path.startsWith("/classroom/") ||
                path.startsWith("/clubroom/") ||
                path.startsWith("/signup/")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                jwtAuth.validateToken(token);
                String userId = jwtAuth.innerIdFromToken(token);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid JWT token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
