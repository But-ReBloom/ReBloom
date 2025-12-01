package com.but.rebloom.domain.auth.usecase;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.dto.request.ChangeActivityRequest;
import com.but.rebloom.domain.auth.dto.request.UpdateUserIdRequest;
import com.but.rebloom.domain.auth.dto.request.UpdateUserPasswordRequest;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.hobby.domain.Activity;
import com.but.rebloom.domain.hobby.exception.ActivityNotFoundException;
import com.but.rebloom.domain.hobby.repository.ActivityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserInfoUseCase {
    // 현재 유저 조회
    private final FindCurrentUserUseCase findCurrentUserUseCase;
    private final DefaultUserUseCase defaultUserUseCase;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

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

    @Transactional
    public User updateUserCurrentActivity(ChangeActivityRequest request) {
        User user = findCurrentUserUseCase.getCurrentUser();

        Activity activity = activityRepository.findById(request.getActivityId())
                .orElseThrow(() -> new ActivityNotFoundException("활동이 조회되지 않음"));

        if (!activity.getUser().getUserEmail().equals(user.getUserEmail())) {
            throw new ActivityNotFoundException("활동이 조회되지 않음");
        }

        user.setUserCurrentActivity(activity);
        return userRepository.save(user);
    }
}
