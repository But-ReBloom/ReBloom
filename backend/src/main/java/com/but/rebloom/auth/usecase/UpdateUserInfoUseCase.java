package com.but.rebloom.auth.usecase;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.dto.request.SendVerificationEmailRequest;
import com.but.rebloom.auth.dto.request.UpdateIdRequest;
import com.but.rebloom.auth.dto.request.UpdatePwRequest;
import com.but.rebloom.auth.repository.UserRepository;
import com.but.rebloom.common.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateUserInfoUseCase {
    // 디비 접근
    private final UserRepository userRepository;
    // 비밀번호 암호화
    private final PasswordEncoder passwordEncoder;
    // 예외 분리
    private final ValidationUseCase validationUseCase;

    public User updateUserId(UpdateIdRequest updateIdRequest) {
        String userId = updateIdRequest.getUserId();
        String userEmail = updateIdRequest.getUserEmail();

        validationUseCase.checkNull(userEmail);
        validationUseCase.checkUserEmail(userEmail);

        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
        User user = optionalUser.orElseThrow(() ->
                new UserNotFoundException("이메일이 조회되지 않음"));

        validationUseCase.checkNull(userId);
        validationUseCase.checkUserId(userId);
        validationUseCase.checkExistAccountByUserId(userId);

        // 디비 수정
        userRepository.updateUserId(userEmail, userId);

        return user;
    }

    public User updateUserPassword(UpdatePwRequest updatePwRequest) {
        String userPassword = updatePwRequest.getUserPassword();
        String userEmail = updatePwRequest.getUserEmail();

        validationUseCase.checkNull(userEmail);
        validationUseCase.checkUserEmail(userEmail);

        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
        User user = optionalUser.orElseThrow(() ->
                new UserNotFoundException("이메일이 조회되지 않음"));

        validationUseCase.checkNull(userPassword);
        validationUseCase.checkUserPassword(userPassword);

        // 디비 수정
        userRepository.updateUserId(userEmail, passwordEncoder.encode(userPassword));

        return user;
    }
}
