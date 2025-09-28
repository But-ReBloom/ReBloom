package com.but.rebloom.auth.controller;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.usecase.FindCurrentUserUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TestController {
    // 현재 유저 정보 확인
    private final FindCurrentUserUseCase findCurrentUserUseCase;

    @GetMapping("/debug/auth")
    public ResponseEntity<?> debugAuth(HttpServletRequest request, Authentication auth) {
        String authHeader = request.getHeader("Authorization");

        Map<String, Object> debug = new HashMap<>();
        debug.put("authHeader", authHeader);
        debug.put("hasAuth", auth != null);
        debug.put("authName", auth != null ? auth.getName() : "null");
        debug.put("isAuthenticated", auth != null ? auth.isAuthenticated() : false);

        return ResponseEntity.ok(debug);
    }

    @PostMapping("/main")
    public ResponseEntity<Object> findCurrentUser() {
        User user = findCurrentUserUseCase.getCurrentUser();
        return ResponseEntity.ok(user.getUserEmail());
    }
}
