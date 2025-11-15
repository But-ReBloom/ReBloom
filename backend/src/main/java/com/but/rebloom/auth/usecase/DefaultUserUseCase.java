package com.but.rebloom.auth.usecase;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.exception.AlreadyUsingUserException;
import com.but.rebloom.auth.exception.UserNotFoundException;
import com.but.rebloom.auth.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserUseCase {
    // 디비 접근
    private final UserRepository userRepository;
    // 예와 이용
    private final AuthValidationUseCase authValidationUseCase;
    // 비밀번호 암호화
    private final PasswordEncoder passwordEncoder;

    // 유저 아이디 수정
    @Transactional
    public User updateUserId(String userEmail, String userNewId) {
        authValidationUseCase.checkUserId(userNewId);

        if (userRepository.existsByUserId(userNewId))
            throw new AlreadyUsingUserException("이미 사용중인 아이디");

        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("유저가 조회되지 않음"));

        user.setUserId(userNewId);

        return user;
    }

    // 유저 비밀번호 수정
    @Transactional
    public User updateUserPassword(String userEmail, String userNewPassword) {
        authValidationUseCase.checkUserPassword(userNewPassword);

        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("유저가 조회되지 않음"));

        user.setUserPassword(passwordEncoder.encode(userNewPassword));

        return user;
    }
}
