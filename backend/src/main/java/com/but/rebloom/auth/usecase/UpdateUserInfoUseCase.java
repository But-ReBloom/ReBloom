package com.but.rebloom.auth.usecase;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.dto.request.UpdateIdRequest;
import com.but.rebloom.auth.dto.request.UpdatePwRequest;
import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.auth.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserInfoUseCase {
    // 디비 접근
    private final UserRepository userRepository;
    // 비밀번호 암호화
    private final PasswordEncoder passwordEncoder;
    // 예외 분리
    private final AuthValidationUseCase authValidationUseCase;

    @Transactional
    public User updateUserId(UpdateIdRequest updateIdRequest) {
        String userId = updateIdRequest.getUserId();
        String userEmail = updateIdRequest.getUserEmail();

        authValidationUseCase.checkNull(userEmail);
        authValidationUseCase.checkUserEmail(userEmail);

        userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("이메일이 조회되지 않음"));

        authValidationUseCase.checkNull(userId);
        authValidationUseCase.checkUserId(userId);
        authValidationUseCase.checkExistAccountByUserId(userId);

        // 디비 수정
        userRepository.updateUserIdByUserEmail(userEmail, userId);

        return userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("이메일이 조회되지 않음"));
    }

    @Transactional
    public User updateUserPw(UpdatePwRequest updatePwRequest) {
        String userPassword = updatePwRequest.getUserPassword();
        String userEmail = updatePwRequest.getUserEmail();

        authValidationUseCase.checkNull(userEmail);
        authValidationUseCase.checkUserEmail(userEmail);

        userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("이메일이 조회되지 않음"));

        authValidationUseCase.checkNull(userPassword);
        authValidationUseCase.checkUserPassword(userPassword);

        // 디비 수정
        userRepository.updateUserPasswordByUserEmail(userEmail, passwordEncoder.encode(userPassword));

        return userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("이메일이 조회되지 않음"));
    }
}
