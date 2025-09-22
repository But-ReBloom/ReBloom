package com.but.rebloom.auth.usecase;

import com.but.rebloom.auth.dto.request.SignupRequest;
import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.common.exception.AlreadyUsingIdException;
import com.but.rebloom.common.exception.IllegalArgumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationUseCase {
    private final UserRepository userRepository;

    public void checkNull(SignupRequest signupRequest) {
        String userEmail = signupRequest.getUserEmail();
        String userId = signupRequest.getUserId();
        String userPassword = signupRequest.getUserPassword();
        String userName = signupRequest.getUserName();

        if (userEmail.isEmpty() || userId.isEmpty() || userPassword.isEmpty() || userName.isEmpty()) {
            throw new IllegalArgumentException("빈 값이 존재");
        }
    }

    public void checkUserId(String userId) {
        if (userId.length() < 4 || userId.length() > 20) {
            throw new IllegalArgumentException("길이가 잘못됨");
        }
        if (!userId.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("영어, 숫자 이외의 글자");
        }
        if (userId.matches(".*(.)\\1{2,}.*")) {
            throw new IllegalArgumentException("3글자 이상 연속");
        }
        if (!userId.matches("^(?=.*[A-Za-z])(?=.*\\d).+$")) {
            throw new IllegalArgumentException("영어, 숫자 포함 안 됨");
        }
    }

    public void checkUserPassword(String userPassword) {
        if (userPassword.length() < 6 || userPassword.length() > 20) {
            throw new IllegalArgumentException("길이가 잘못됨");
        }
        if (!userPassword.matches("^[A-Za-z0-9@$!%*?&]+$")) {
            throw new IllegalArgumentException("영어, 숫자, 특수문자 이외의 글자");
        }
        if (userPassword.matches(".*(.)\\1{2,}.*")) {
            throw new IllegalArgumentException("3글자 이상 연속");
        }
        if (!userPassword.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&]).+$")) {
            throw new IllegalArgumentException("영어, 숫자, 특수문자를 포함 안 됨");
        }
    }

    public void checkUserEmail(String userEmail) {
        if (!userEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("이메일 오류");
        }
    }

    public void checkUserName(String userName) {
        if (userName.length() < 4 || userName.length() > 20) {
            throw new IllegalArgumentException("이름 오류");
        }
    }

    public void checkExistAccount(String userEmail, String userId) {
        if (userRepository.existsByUserEmail(userEmail) ||
                userRepository.existsByUserId(userId)) {
            throw new AlreadyUsingIdException("이미 존재함");
        }
    }
}
