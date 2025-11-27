package com.but.rebloom.domain.hobby.usecase;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.hobby.exception.ActivityNotFoundException;
import com.but.rebloom.domain.hobby.domain.Activity;
import com.but.rebloom.domain.hobby.dto.request.AddActivityRequest;
import com.but.rebloom.domain.hobby.repository.ActivityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultActivityUseCase {
    // 디비 이용
    private final ActivityRepository activityRepository;
    // 취미 예외처리 이용
    private final HobbyValidationUseCase hobbyValidationUseCase;
    // 로그인중인 유저 정보 추출에 이용
    private final FindCurrentUserUseCase findCurrentUserUseCase;
    private final UserRepository userRepository;

    // Activity 조회 - ActivityId
    public Activity findActivityByActivityId(Long activityId) {
        String userEmail = findCurrentUserUseCase.getCurrentUser().getUserEmail();

        return activityRepository.findByUser_UserEmailAndActivityId(userEmail, activityId)
                .orElseThrow(() -> new ActivityNotFoundException("활동 조회 실패"));
    }

    // Activity 조회 - ActivityName
    public Activity findActivityByActivityName(String activityName) {
        String userEmail = findCurrentUserUseCase.getCurrentUser().getUserEmail();

        return activityRepository.findByUser_UserEmailAndActivityName(userEmail, activityName)
                .orElseThrow(() -> new ActivityNotFoundException("활동 조회 실패"));
    }

    // 전체 Activity 조회
    public List<Activity> findAllActivity() {
        String userEmail = findCurrentUserUseCase.getCurrentUser().getUserEmail();

        return activityRepository.findByUser_UserEmail(userEmail);
    }

    // Activity 조회 - ActivityRecent(ASC)
    public List<Activity> findActivityOrderByActivityRecentAsc() {
        User user = findCurrentUserUseCase.getCurrentUser();
        String userEmail = user.getUserEmail();

        return activityRepository.findByUser_UserEmailOrderByActivityRecentAsc(userEmail);
    }

    // Activity 조회 - ActivityRecent(DESC)
    public List<Activity> findActivityOrderByActivityRecentDesc() {
        User user = findCurrentUserUseCase.getCurrentUser();
        String userEmail = user.getUserEmail();

        return activityRepository.findByUser_UserEmailOrderByActivityRecentDesc(userEmail);
    }

    // Activity 추가
    @Transactional
    public Activity addActivity(AddActivityRequest request) {
        String activityName = request.getActivityName();

        String userEmail = findCurrentUserUseCase.getCurrentUser().getUserEmail();

        // 각 요소별 예외처리
        hobbyValidationUseCase.checkActivityName(activityName);

        // 이미 활동중인 활동인지 확인
        hobbyValidationUseCase.checkExistActivityByEmailAndActivityName(userEmail, activityName);

        // 활동 생성
        Activity activity = Activity.builder()
                .activityName(activityName)
                .user(userRepository.findByUserEmail(userEmail)
                        .orElseThrow(() -> new UserNotFoundException("유저가 조회되지 않음"))
                )
                .build();

        // 활동 추가
        return activityRepository.save(activity);
    }
}
