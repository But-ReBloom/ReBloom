package com.but.rebloom.domain.auth.usecase;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.dto.request.UpdateUserIdRequest;
import com.but.rebloom.domain.auth.dto.request.UpdateUserPasswordRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserInfoUseCase {
    // 현재 유저 조회
    private final FindCurrentUserUseCase findCurrentUserUseCase;
    private final DefaultUserUseCase defaultUserUseCase;

    @Transactional
    public User updateUserId(UpdateUserIdRequest request) {
        String updateUserId = request.getUpdateUserId();
        String userEmail = findCurrentUserUseCase.getCurrentUser().getUserEmail();

        return defaultUserUseCase.updateUserId(userEmail, updateUserId);
    }

    @Transactional
    public User updateUserPassword(UpdateUserPasswordRequest request) {
        String updateUserPassword = request.getUpdateUserPassword();
        String userEmail = findCurrentUserUseCase.getCurrentUser().getUserEmail();

        return defaultUserUseCase.updateUserPassword(userEmail, updateUserPassword);
    }
}
