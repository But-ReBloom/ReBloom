package com.but.rebloom.auth.controller;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.usecase.FindCurrentUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    // 현재 유저 정보 확인
    private final FindCurrentUserUseCase findCurrentUserUseCase;

    @PostMapping("/main")
    public ResponseEntity<Object> findCurrentUser() {
        User user = findCurrentUserUseCase.getCurrentUser();
        return ResponseEntity.ok(user.getUserEmail());
    }
}
