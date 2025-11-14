package com.but.rebloom.auth.controller;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.dto.request.*;
import com.but.rebloom.auth.dto.response.*;
import com.but.rebloom.auth.jwt.JwtTokenProvider;
import com.but.rebloom.auth.usecase.*;
import com.but.rebloom.common.dto.ApiResponse;
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
    // 토큰 발급에 이용
    private final JwtTokenProvider jwtTokenProvider;
    // 현재 유저 정보 확인
    private final FindCurrentUserUseCase findCurrentUserUseCase;

    @PostMapping("/email/send")
    public ResponseEntity<ApiResponse<GetUserEmailResponse>> sendVerificationEmail(@RequestBody SendVerificationEmailRequest sendVerificationEmailRequest) {
        // 인증 코드 저장함
        User user = emailUseCase.sendVerificationEmail(sendVerificationEmailRequest);
        return ResponseEntity.ok(ApiResponse.success(GetUserEmailResponse.from(user)));
    }

    @PostMapping("/email/verify")
    public ResponseEntity<ApiResponse<GetUserEmailResponse>> verifyCode(@RequestBody VerifyCodeRequest verifyCodeRequest) {
        // 인증 코드 인증 로직 실행
        User user = emailUseCase.verifyCode(verifyCodeRequest);
        return ResponseEntity.ok(ApiResponse.success(GetUserEmailResponse.from(user)));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignupResponse>> signup(@RequestBody SignupRequest signupRequest) {
        // 회원가입 로직 실행
        User user = signupUseCase.signup(signupRequest);
        return ResponseEntity.ok(ApiResponse.success(SignupResponse.from(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        // 로그인 로직 실행
        User user = loginUseCase.login(loginRequest);
        String jwtToken = jwtTokenProvider.generateToken(user.getUserEmail());
        return ResponseEntity.ok(ApiResponse.success(LoginResponse.from(user, jwtToken)));
    }

    @PostMapping("/login/google")
    public ResponseEntity<ApiResponse<GoogleUserInfoResponse>> googleLogin(@RequestBody GoogleLoginAuthorizeCodeRequest request) {
        User user = googleOAuthUseCase.execute(request);
        String jwtToken = jwtTokenProvider.generateToken(user.getUserEmail());
        return ResponseEntity.ok(ApiResponse.success(GoogleUserInfoResponse.from(user, jwtToken)));
    }

    @PatchMapping("/update/id")
    public ResponseEntity<ApiResponse<UpdateIdResponse>> updateUserId(@RequestBody UpdateUserIdRequest request) {
        // 아이디 변경 및 반환
        User user = updateUserInfoUseCase.updateUserId(request);
        return ResponseEntity.ok(ApiResponse.success(UpdateIdResponse.from(user)));
    }

    @PatchMapping("/update/pw")
    public ResponseEntity<ApiResponse<GetUserEmailResponse>> updateUserPw(@RequestBody UpdateUserPasswordRequest request) {
        // 비밀번호 변경
        User user = updateUserInfoUseCase.updateUserPw(request);
        return ResponseEntity.ok(ApiResponse.success(GetUserEmailResponse.from(user)));
    }

    @PostMapping("/find/email")
    public ResponseEntity<ApiResponse<GetUserEmailResponse>> findUserEmail(@RequestBody FindEmailRequest findEmailRequest) {
        // 이메일 조회
        User user = findUserInfoUseCase.findUserIdByIdAndPw(findEmailRequest);
        return ResponseEntity.ok(ApiResponse.success(GetUserEmailResponse.from(user)));
    }

    @PostMapping("/current-user")
    public ResponseEntity<ApiResponse<FindUserInfoResponse>> findCurrentUser() {
        User user = findCurrentUserUseCase.getCurrentUser();
        return ResponseEntity.ok(ApiResponse.success(FindUserInfoResponse.from(user)));
    }
}