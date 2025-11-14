package com.but.rebloom.auth.usecase;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.dto.request.UpdateUserIdRequest;
import com.but.rebloom.auth.dto.request.UpdateUserPasswordRequest;
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
    // 현재 유저 조회
    private final FindCurrentUserUseCase findCurrentUserUseCase;

    @Transactional
    public User updateUserId(UpdateUserIdRequest request) {
        String updateUserId = request.getUpdateUserId();

        String userEmail = findCurrentUserUseCase.getCurrentUser().getUserEmail();

        authValidationUseCase.checkNull(userEmail);

        authValidationUseCase.checkNull(updateUserId);
        authValidationUseCase.checkUserId(updateUserId);
        authValidationUseCase.checkExistAccountByUserId(updateUserId);

        // 디비 수정
        userRepository.updateUserIdByUserEmail(userEmail, updateUserId);

        return userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("이메일이 조회되지 않음"));
    }

    @Transactional
    public User updateUserPw(UpdateUserPasswordRequest request) {
        String userEmail = findCurrentUserUseCase.getCurrentUser().getUserEmail();
        String updateUserPw = request.getUpdateUserPassword();

        authValidationUseCase.checkNull(userEmail);

        authValidationUseCase.checkNull(updateUserPw);
        authValidationUseCase.checkUserPassword(updateUserPw);

        // 디비 수정
        userRepository.updateUserPasswordByUserEmail(userEmail, passwordEncoder.encode(updateUserPw));

        return userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("이메일이 조회되지 않음"));
    }
}
