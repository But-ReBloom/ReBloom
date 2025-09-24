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

    public void hostEmailVerifyWithUserId(UpdateIdRequest updateIdRequest, SendVerificationEmailRequest sendVerificationEmailRequest) {
        emailUseCase.sendVerificationEmail(sendVerificationEmailRequest);
    }

    public String updateUserId(UpdateIdRequest updateIdRequest, SendVerificationEmailRequest sendVerificationEmailRequest) {
        String userId = updateIdRequest.getUserId();

        validationUseCase.checkNull(userId);
        validationUseCase.checkUserId(userId);
        validationUseCase.checkUserId(userId);

        emailUseCase.sendVerificationEmail(sendVerificationEmailRequest);

        userRepository.updateUserId(sendVerificationEmailRequest.getUserEmail(), userId);

        return "";
    }
}
