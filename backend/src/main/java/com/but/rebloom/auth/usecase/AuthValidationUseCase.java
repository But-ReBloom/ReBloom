package com.but.rebloom.auth.usecase;

import com.but.rebloom.auth.dto.request.LoginRequest;
import com.but.rebloom.auth.dto.request.SignupRequest;
import com.but.rebloom.auth.exception.AlreadyUsingUserException;
import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.common.exception.IllegalArgumentException;
import com.but.rebloom.common.usecase.ValidationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthValidationUseCase {
    // DI
    private final ValidationUseCase validationUseCase;
    // 디비 이용
    private final UserRepository userRepository;

    // 널 값 확인 - 한 요소
    public <T> void checkNull(T element) {
        validationUseCase.checkNull(element);
    }

    // 널 값 확인 - 로그인
    public void checkNull(LoginRequest loginRequest) {
        String userEmail = loginRequest.getUserEmail();
        String userPassword = loginRequest.getUserPassword();

        if (userEmail.isEmpty() || userPassword.isEmpty()) {
            throw new IllegalArgumentException("빈 값이 존재");
        }
    }

    // 널 값 확인 - 회원가입
    public void checkNull(SignupRequest signupRequest) {
        String userEmail = signupRequest.getUserEmail();
        String userId = signupRequest.getUserId();
        String userPassword = signupRequest.getUserPassword();
        String userName = signupRequest.getUserName();

        if (userEmail.isEmpty() || userId.isEmpty() || userPassword.isEmpty() || userName.isEmpty()) {
            throw new IllegalArgumentException("빈 값이 존재");
        }
    }

    // 아이디 확인
    public void checkUserId(String userId) {
        validationUseCase.checkUserId(userId);
    }

    // 비밀번호 확인
    public void checkUserPassword(String userPassword) {
        validationUseCase.checkUserPassword(userPassword);
    }

    // 이메일 확인
    public void checkUserEmail(String userEmail) {
        validationUseCase.checkUserEmail(userEmail);
    }

    // 이름 확인
    public void checkUserName(String userName) {
        validationUseCase.checkUserName(userName);
    }

    // 존재하는지 확인
    public void checkExistAccount(String userEmail, String userId) {
        validationUseCase.checkExistAccount(userEmail, userId);
    }

    // 존재하는지 확인
    public void checkExistAccountByUserEmail(String userEmail) {
        validationUseCase.checkExistAccountByUserEmail(userEmail);
    }

    // 존재하는지 확인
    public void checkExistAccountByUserId(String userId) {
        validationUseCase.checkExistAccountByUserId(userId);
    }
}
