package com.but.rebloom.auth.usecase;

import com.but.rebloom.auth.domain.Provider;
import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.dto.request.LoginRequest;
import com.but.rebloom.auth.jwt.JwtTokenProvider;
import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.common.exception.IllegalArgumentException;
import com.but.rebloom.common.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginUseCase {
    // 디비 접근
    private final UserRepository userRepository;
    // 예외 이용
    private final ValidationUseCase validationUseCase;
    // 비밀번호 암호화
    private final PasswordEncoder passwordEncoder;
    // Jwt 토큰 생성
    private final JwtTokenProvider jwtTokenProvider;

    // 로그인, 토큰 반환
    public Map<User, Object> login(LoginRequest loginRequest) {
        // 기본 예외 확인
        validationUseCase.checkNull(loginRequest);

        String userEmail = loginRequest.getUserEmail();
        String userPassword = loginRequest.getUserPassword();

        validationUseCase.checkUserEmail(userEmail);
        validationUseCase.checkUserPassword(userPassword);

        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);

        // 유저 조회
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("유저가 조회되지 않음");
        }

        User user = optionalUser.get();

        if (!loginRequest.getProvider().equals(Provider.SELF)) {
            throw new IllegalArgumentException("잘못된 로그인 환경");
        }

        if (!passwordEncoder.matches(userPassword, user.getUserPassword())) {
            throw new UserNotFoundException("유저 조회 실패");
        }

        // 토큰 생성 및 반환
        return Map.of(user, jwtTokenProvider.generateToken(String.valueOf(user.getUserEmail())));
    }
}
