package com.but.rebloom.auth.usecase;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.dto.request.FindEmailRequest;
import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.common.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindUserInfoUseCase {
    // 디비 접근
    private final UserRepository userRepository;
    // 예외 호출
    private final ValidationUseCase validationUseCase;
    // 비밀번호 암호화
    private final PasswordEncoder passwordEncoder;

    // 이메일 찾기
    public User findUserIdByIdAndPw(FindEmailRequest findEmailRequest) {
        String userId = findEmailRequest.getUserId();
        String userPassword = findEmailRequest.getPassword();

        validationUseCase.checkUserId(userId);
        validationUseCase.checkUserPassword(userPassword);

        return userRepository.findByUserId(userId)
                // 필터로 비밀번호 매치 로직 추가
                .filter(user -> passwordEncoder.matches(userPassword, user.getUserPassword()))
                .orElseThrow(() -> new UserNotFoundException("아이디 또는 비밀번호가 올바르지 않음"));
    }
}
