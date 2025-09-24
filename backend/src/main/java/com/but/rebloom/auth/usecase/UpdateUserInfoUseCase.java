package com.but.rebloom.auth.usecase;

import com.but.rebloom.auth.dto.request.SendVerificationEmailRequest;
import com.but.rebloom.auth.dto.request.UpdateIdRequest;
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
    // 이메일 인증
    private final EmailUseCase emailUseCase;
    // 예외 분리
    private final ValidationUseCase validationUseCase;

    public String updateUserId(UpdateIdRequest updateIdRequest) {
        String userId = updateIdRequest.getUserId();

        validationUseCase.checkNull(userId);
        validationUseCase.checkUserId(userId);
        validationUseCase.checkUserId(userId);

        // 디비 수정
        userRepository.updateUserId(sendVerificationEmailRequest.getUserEmail(), userId);

        return userId;
    }
}
