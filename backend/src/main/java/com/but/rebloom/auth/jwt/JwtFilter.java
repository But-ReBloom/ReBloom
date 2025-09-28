package com.but.rebloom.auth.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

//@Component
//@RequiredArgsConstructor
//public class JwtFilter extends OncePerRequestFilter {
//
//    private final JwtAuthenticationFilter jwtAuth;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String path = request.getRequestURI();
//
//
//        // 필터 지정
//        if (path.equals("/favicon.ico") ||
//                path.startsWith("/.well-known/") ||
//                path.equals("/") ||
//                path.startsWith("/auth/") ||
//                path.startsWith("/email/") ||
//                path.startsWith("/swagger-ui/") ||
//                path.startsWith("/v3/api-docs/") ||
//                path.startsWith("/api-docs")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String authHeader = request.getHeader("Authorization");
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            try {
//                jwtAuth.validateToken(token);
//                String userEmail = jwtAuth.userEmailFromToken(token);
//                Authentication authentication = new UsernamePasswordAuthenticationToken(userEmail, null, Collections.emptyList());
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } catch (Exception e) {
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                response.getWriter().write("Invalid JWT token");
//                return;
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}


@Component
@RequiredArgsConstructor
@Slf4j // 이걸 추가하거나 System.out.println 사용
public class JwtFilter extends OncePerRequestFilter {

    private final JwtAuthenticationFilter jwtAuth;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        System.out.println("🚀 JwtFilter 실행! Path: " + path);

        // 필터 지정
        if (path.equals("/favicon.ico") ||
                path.startsWith("/.well-known/") ||
                path.equals("/") ||
                path.startsWith("/auth/") ||
                path.startsWith("/email/") ||
                path.startsWith("/swagger-ui/") ||
                path.startsWith("/v3/api-docs/") ||
                path.startsWith("/api-docs")) {
            System.out.println("⏭️ 필터 건너뜀: " + path);
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        System.out.println("🔑 Authorization 헤더: " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            System.out.println("✂️ 토큰 추출: " + token.substring(0, 20) + "...");

            try {
                System.out.println("🔍 토큰 검증 시작...");
                jwtAuth.validateToken(token);
                System.out.println("✅ 토큰 검증 성공!");

                String userEmail = jwtAuth.userEmailFromToken(token);
                System.out.println("📧 사용자 이메일: " + userEmail);

                Authentication authentication = new UsernamePasswordAuthenticationToken(userEmail, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("🎯 SecurityContext에 인증 정보 설정 완료!");

            } catch (Exception e) {
                System.out.println("❌ JWT 검증 실패: " + e.getMessage());
                e.printStackTrace(); // 스택 트레이스도 출력
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid JWT token");
                return;
            }
        } else {
            System.out.println("⚠️ Authorization 헤더가 없거나 Bearer로 시작하지 않음");
        }

        System.out.println("➡️ 다음 필터로 진행...");
        filterChain.doFilter(request, response);
    }
}