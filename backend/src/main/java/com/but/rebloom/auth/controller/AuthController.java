package com.but.rebloom.auth.controller;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.dto.request.*;
import com.but.rebloom.auth.dto.response.*;
import com.but.rebloom.auth.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    // Google OAuth에 이용
    private final GoogleOAuthUseCase googleOAuthUseCase;
    // 유저 정보 찾기에 이용
    private final FindUserInfoUseCase findUserInfoUseCase;

    @PostMapping("/email/send")
    public ResponseEntity<Object> sendVerificationEmail(@RequestBody SendVerificationEmailRequest sendVerificationEmailRequest) {
        // 인증 코드 저장함
        User user = emailUseCase.sendVerificationEmail(sendVerificationEmailRequest);
        return ResponseEntity.ok(SendVerificationEmailResponse.from(user));
    }

    @PostMapping("/email/verify")
    public ResponseEntity<Object> verifyCode(@RequestBody VerifyCodeRequest verifyCodeRequest) {
        // 인증 코드 인증 로직 실행
        User user = emailUseCase.verifyCode(verifyCodeRequest);
        return ResponseEntity.ok(VerifyCodeResponse.from(user));
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
        LoginResponse loginResponse = loginUseCase.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/login/google")
    public ResponseEntity<GoogleUserInfoResponse> googleLogin(@RequestBody GoogleLoginRequest request) {
        GoogleUserInfoResponse response = googleOAuthUseCase.execute(request.getAuthorizationCode());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update/id")
    public ResponseEntity<Object> updateUserId(@RequestBody UpdateIdRequest updateIdRequest) {
        // 아이디 변경 및 반환
        User user = updateUserInfoUseCase.updateUserId(updateIdRequest);
        return ResponseEntity.ok(UpdateIdResponse.from(user));
    }

    @PostMapping("/update/pw")
    public ResponseEntity<Object> updateUserId(@RequestBody UpdatePwRequest updatePwRequest) {
        // 비밀번호 변경
        User user = updateUserInfoUseCase.updateUserPw(updatePwRequest);
        return ResponseEntity.ok(UpdatePwResponse.from(user));
    }

    @PostMapping("/find/email")
    public ResponseEntity<Object> findUserEmail(@RequestBody FindEmailRequest findEmailRequest) {
        // 이메일 조회
        User user = findUserInfoUseCase.findUserIdByIdAndPw(findEmailRequest);
        return ResponseEntity.ok(FindEmailResponse.from(user));
    }


}
