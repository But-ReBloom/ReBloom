package com.but.rebloom.auth.controller;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.dto.request.*;
import com.but.rebloom.auth.dto.response.LoginResponse;
import com.but.rebloom.auth.dto.response.SendVerificationEmailResponse;
import com.but.rebloom.auth.dto.response.SignupResponse;
import com.but.rebloom.auth.usecase.EmailUseCase;
import com.but.rebloom.auth.usecase.LoginUseCase;
import com.but.rebloom.auth.usecase.SignupUseCase;
import com.but.rebloom.auth.usecase.UpdateUserInfoUseCase;
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
    // 이메일 인증 부분에 이용
    private final EmailUseCase emailUseCase;
    // 회원가입 부분에 이용
    private final SignupUseCase signupUseCase;
    // 로그인 부분에 이용
    private final LoginUseCase loginUseCase;
    // 정보 수정 부분에 이용
    private final UpdateUserInfoUseCase updateUserInfoUseCase;

    @PostMapping("/email/send")
    public ResponseEntity<Object> sendVerificationEmail(@RequestBody SendVerificationEmailRequest sendVerificationEmailRequest) {
        // 인증 코드 저장함
        Map<User, String> response = emailUseCase.sendVerificationEmail(sendVerificationEmailRequest);
        return ResponseEntity.ok(SendVerificationEmailResponse.from(response));
    }

    @PostMapping("/email/verify")
    public ResponseEntity<Object> verifyCode(@RequestBody VerifyCodeRequest verifyCodeRequest) {
        // 인증 코드 인증 로직 실행
        emailUseCase.verifyCode(verifyCodeRequest);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "code", verifyCodeRequest.getCode()
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignupRequest signupRequest) {
        // 회원가입 로직 실행
        User user = signupUseCase.signup(signupRequest);
        return ResponseEntity.ok(SignupResponse.from(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        // 로그인 로직 실행
        Map<User, Object> loginResponse = loginUseCase.login(loginRequest);
        return ResponseEntity.ok(LoginResponse.from(loginResponse));
    }

    @PostMapping("/update/id")
    public ResponseEntity<Object> updateUserId(@RequestBody UpdateIdRequest updateIdRequest) {
        // 아이디 변경 및 반환
        String newUserId = updateUserInfoUseCase.updateUserId(updateIdRequest);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "userEmail", updateIdRequest.getUserEmail(),
                "userNewId", newUserId
        ));
    }

    @PostMapping("/update/pw")
    public ResponseEntity<Object> updateUserId(@RequestBody UpdatePwRequest updatePwRequest) {
        // 비밀번호 변경
        updateUserInfoUseCase.updateUserPassword(updatePwRequest);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "userEmail", updatePwRequest.getUserEmail()
        ));
    }
}
