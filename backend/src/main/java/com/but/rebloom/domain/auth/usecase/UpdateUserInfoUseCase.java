package com.but.rebloom.domain.auth.usecase;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.dto.request.UpdateUserIdRequest;
import com.but.rebloom.domain.auth.dto.request.UpdateUserPasswordRequest;
import com.but.rebloom.domain.auth.jwt.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserInfoUseCase {
    // 현재 유저 조회
    private final FindCurrentUserUseCase findCurrentUserUseCase;
    private final DefaultUserUseCase defaultUserUseCase;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public User updateUserId(String token, UpdateUserIdRequest request) {
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String updateUserId = request.getUpdateUserId();
        String userEmail = findCurrentUserUseCase.getCurrentUser().getUserEmail();

        return defaultUserUseCase.updateUserId(userEmail, updateUserId);
    }

    @Transactional
    public User updateUserPassword(String token, UpdateUserPasswordRequest request) {
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String updateUserPassword = request.getUpdateUserPassword();
        String userEmail = findCurrentUserUseCase.getCurrentUser().getUserEmail();

        return defaultUserUseCase.updateUserPassword(userEmail, updateUserPassword);
    }
}
