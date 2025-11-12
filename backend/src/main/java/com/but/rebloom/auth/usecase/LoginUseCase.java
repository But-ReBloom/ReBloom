package com.but.rebloom.auth.usecase;

import com.but.rebloom.auth.domain.Provider;
import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.dto.request.LoginRequest;
import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.common.exception.IllegalArgumentException;
import com.but.rebloom.auth.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCase {
    // 디비 접근
    private final UserRepository userRepository;
    // 예외 이용
    private final AuthValidationUseCase authValidationUseCase;
    // 비밀번호 암호화
    private final PasswordEncoder passwordEncoder;

    // 로그인
    public User login(LoginRequest loginRequest) {
        // 기본 예외 확인
        authValidationUseCase.checkNull(loginRequest);

        String userEmail = loginRequest.getUserEmail();
        String userPassword = loginRequest.getUserPassword();

        authValidationUseCase.checkUserEmail(userEmail);
        authValidationUseCase.checkUserPassword(userPassword);

        if (!loginRequest.getUserProvider().equals(Provider.SELF)) {
            throw new IllegalArgumentException("잘못된 로그인 환경");
        }

        return userRepository.findByUserEmail(userEmail)
                .filter(user -> passwordEncoder.matches(userPassword, user.getUserPassword()))
                .orElseThrow(() -> new UserNotFoundException("유저 조회 실패"));
    }
}