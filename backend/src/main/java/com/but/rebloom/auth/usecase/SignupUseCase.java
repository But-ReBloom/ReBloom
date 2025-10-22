package com.but.rebloom.auth.usecase;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.dto.request.SignupRequest;
import com.but.rebloom.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupUseCase {
    // 디비 접근
    private final UserRepository userRepository;
    // 비밀번호 암호화
    private final PasswordEncoder passwordEncoder;
    // 예외 이용
    private final ValidationUseCase validationUseCase;

    // 회원가입
    public User signup(SignupRequest signupRequest) {
        // 기본 예외 처리
        validationUseCase.checkNull(signupRequest);

        String userEmail = signupRequest.getUserEmail();
        String userId = signupRequest.getUserId();
        String userPassword = signupRequest.getUserPassword();
        String userName = signupRequest.getUserName();

        validationUseCase.checkUserEmail(userEmail);
        validationUseCase.checkUserId(userId);
        validationUseCase.checkUserPassword(userPassword);
        validationUseCase.checkUserName(userName);

        validationUseCase.checkExistAccount(userEmail, userId);

        // 유저 생성
        User user = User.builder()
                .userEmail(userEmail)
                .userId(userId)
                .userPassword(passwordEncoder.encode(userPassword))
                .userName(userName)
                .provider(signupRequest.getProvider())
                .build();

        // 유저 등록
        return userRepository.save(user);
    }
}
