package com.but.rebloom.domain.auth.controller;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.dto.request.*;
import com.but.rebloom.domain.auth.dto.response.*;
import com.but.rebloom.domain.auth.jwt.JwtTokenProvider;
import com.but.rebloom.domain.auth.usecase.*;
import com.but.rebloom.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 이메일 전송
    @PostMapping("/email/send")
    public ResponseEntity<ApiResponse<GetUserEmailResponse>> sendVerificationEmail(@RequestBody SendVerificationEmailRequest sendVerificationEmailRequest) {
        // 인증 코드 저장함
        User user = emailUseCase.sendVerificationEmail(sendVerificationEmailRequest);
        return ResponseEntity.ok(ApiResponse.success(GetUserEmailResponse.from(user)));
    }

    // 이메일 인증
    @PostMapping("/email/verify")
    public ResponseEntity<ApiResponse<GetUserEmailResponse>> verifyCode(@RequestBody VerifyCodeRequest verifyCodeRequest) {
        // 인증 코드 인증 로직 실행
        User user = emailUseCase.verifyCode(verifyCodeRequest);
        return ResponseEntity.ok(ApiResponse.success(GetUserEmailResponse.from(user)));
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignupResponse>> signup(@RequestBody SignupRequest signupRequest) {
        User user = signupUseCase.signup(signupRequest);
        return ResponseEntity.ok(ApiResponse.success(SignupResponse.from(user)));
    }

    // 자체 로그인
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        User user = loginUseCase.login(loginRequest);
        String jwtToken = jwtTokenProvider.generateToken(user.getUserEmail());
        return ResponseEntity.ok(ApiResponse.success(LoginResponse.from(user, jwtToken)));
    }

    // OAuth 로그인
    @PostMapping("/login/google")
    public ResponseEntity<ApiResponse<GoogleUserInfoResponse>> googleLogin(@RequestBody GoogleLoginAuthorizeCodeRequest request) {
        User user = googleOAuthUseCase.execute(request);
        String jwtToken = jwtTokenProvider.generateToken(user.getUserEmail());
        return ResponseEntity.ok(ApiResponse.success(GoogleUserInfoResponse.from(user, jwtToken)));
    }

    // 아이디 변경
    @PatchMapping("/update/id")
    public ResponseEntity<ApiResponse<UpdateIdResponse>> updateUserId(
            @RequestBody UpdateUserIdRequest request
    ) {
        User user = updateUserInfoUseCase.updateUserId(request);
        return ResponseEntity.ok(ApiResponse.success(UpdateIdResponse.from(user)));
    }

    // 비밀번호 재설정
    @PatchMapping("/update/pw")
    public ResponseEntity<ApiResponse<GetUserEmailResponse>> updateUserPw(
            @RequestBody UpdateUserPasswordRequest request
    ) {
        User user = updateUserInfoUseCase.updateUserPassword(request);
        return ResponseEntity.ok(ApiResponse.success(GetUserEmailResponse.from(user)));
    }

    // 이메일 찾기
    @PostMapping("/find/email")
    public ResponseEntity<ApiResponse<GetUserEmailResponse>> findUserEmail(@RequestBody FindEmailRequest findEmailRequest) {
        User user = findUserInfoUseCase.findUserEmailByIdAndPw(findEmailRequest);
        return ResponseEntity.ok(ApiResponse.success(GetUserEmailResponse.from(user)));
    }

    // 아이디 찾기
    @PostMapping("/find/id")
    public ResponseEntity<ApiResponse<GetUserIdResponse>> findUserId(@RequestBody FindIdRequest findIdRequest) {
        User user = findUserInfoUseCase.findUserIdByEmailAndPw(findIdRequest);
        return ResponseEntity.ok(ApiResponse.success(GetUserIdResponse.from(user)));
    }

    // 현재 유저 조회
    @PostMapping("/current-user")
    public ResponseEntity<ApiResponse<FindUserInfoResponse>> findCurrentUser() {
        User user = findCurrentUserUseCase.getCurrentUser();
        return ResponseEntity.ok(ApiResponse.success(FindUserInfoResponse.from(user)));
    }

    // Activity 변경
    @PostMapping("/change")
    public ResponseEntity<ApiResponse<ChangeActivityResponse>> changeActivity(@RequestBody ChangeActivityRequest request) {
        User user = updateUserInfoUseCase.updateUserCurrentActivity(request);
        return ResponseEntity.ok(ApiResponse.success(ChangeActivityResponse.from(user)));
    }
}