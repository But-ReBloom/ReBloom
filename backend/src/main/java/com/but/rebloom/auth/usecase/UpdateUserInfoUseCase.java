package com.but.rebloom.auth.usecase;

import com.but.rebloom.auth.dto.request.SendVerificationEmailRequest;
import com.but.rebloom.auth.dto.request.UpdateIdRequest;
import com.but.rebloom.auth.dto.request.UpdatePwRequest;
import com.but.rebloom.auth.repository.UserRepository;
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
    private final ValidationUseCase validationUseCase;

    public String updateUserId(UpdateIdRequest updateIdRequest) {
        String userId = updateIdRequest.getUserId();
        String userEmail = updateIdRequest.getUserEmail();

        validationUseCase.checkNull(userEmail);
        validationUseCase.checkUserEmail(userEmail);

        validationUseCase.checkNull(userId);
        validationUseCase.checkUserId(userId);
        validationUseCase.checkExistAccountByUserId(userId);

        // 디비 수정
        userRepository.updateUserId(userEmail, userId);

        return userId;
    }

    public void updateUserPassword(UpdatePwRequest updatePwRequest) {
        String userPassword = updatePwRequest.getUserPassword();
        String userEmail = updatePwRequest.getUserEmail();

        validationUseCase.checkNull(userEmail);
        validationUseCase.checkUserEmail(userEmail);

        validationUseCase.checkNull(userPassword);
        validationUseCase.checkUserPassword(userPassword);

        // 디비 수정
        userRepository.updateUserId(userEmail, passwordEncoder.encode(userPassword));
    }
}
