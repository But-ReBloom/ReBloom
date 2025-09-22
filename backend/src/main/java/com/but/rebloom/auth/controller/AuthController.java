package com.but.rebloom.auth.controller;

import com.but.rebloom.auth.dto.request.SignupRequest;
import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.auth.usecase.SignupUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final SignupUseCase signupUseCase;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignupRequest signupRequest) {
        signupUseCase.signup(signupRequest);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "userId", signupRequest.getUserId(),
                "userEmail", signupRequest.getUserEmail()
        ));
    }
}
