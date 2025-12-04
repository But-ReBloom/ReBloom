package com.but.rebloom.domain.hobby.usecase;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.exception.UserNotFoundException;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.hobby.exception.ActivityNotFoundException;
import com.but.rebloom.domain.hobby.domain.Activity;
import com.but.rebloom.domain.hobby.dto.request.AddActivityRequest;
import com.but.rebloom.domain.hobby.exception.HobbyNotFoundException;
import com.but.rebloom.domain.hobby.repository.ActivityRepository;
import com.but.rebloom.domain.hobby.repository.HobbyRepository;
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
    private final HobbyRepository hobbyRepository;

    // Activity 조회 - ActivityId
    public Activity findActivityByActivityId(Long activityId) {
        String userEmail = findCurrentUserUseCase.getCurrentUser().getUserEmail();

        Activity activity = activityRepository.findByActivityId(activityId)
                .orElseThrow(() -> new ActivityNotFoundException("활동 조회 실패"));

        if (activity.getUser().getUserEmail().equals(userEmail)) {
            throw new ActivityNotFoundException("활동 조회 실패");
        }

        return activity;
    }

    // 전체 Activity 조회
    public List<Activity> findAllActivity() {
        String userEmail = findCurrentUserUseCase.getCurrentUser().getUserEmail();

        List<Activity> activities = activityRepository.findByUser_UserEmail(userEmail);

        if (activities.isEmpty()) {
            throw new ActivityNotFoundException("활동 조회 실패");
        }

        return activities;
    }

    // Activity 조회 - ActivityRecent(ASC)
    public List<Activity> findActivityOrderByActivityRecentAsc() {
        User user = findCurrentUserUseCase.getCurrentUser();
        String userEmail = user.getUserEmail();

        List<Activity> activities = activityRepository.findByUser_UserEmailOrderByActivityRecentAsc(userEmail);

        if (activities.isEmpty()) {
            throw new ActivityNotFoundException("활동 조회 실패");
        }

        return activities;
    }

    // Activity 조회 - ActivityRecent(DESC)
    public List<Activity> findActivityOrderByActivityRecentDesc() {
        User user = findCurrentUserUseCase.getCurrentUser();
        String userEmail = user.getUserEmail();

        List<Activity> activities = activityRepository.findByUser_UserEmailOrderByActivityRecentDesc(userEmail);

        if (activities.isEmpty()) {
            throw new ActivityNotFoundException("활동 조회 실패");
        }

        return activities;
    }

    // Activity 추가
    @Transactional
    public Activity addActivity(AddActivityRequest request) {
        Long hobbyId = request.getHobbyId();

        String userEmail = findCurrentUserUseCase.getCurrentUser().getUserEmail();

        // 이미 활동중인 활동인지 확인
        hobbyValidationUseCase.checkExistActivityByEmailAndHobby_HobbyId(userEmail, hobbyId);

        // 활동 생성
        Activity activity = Activity.builder()
                .hobby(hobbyRepository.findByHobbyId(request.getHobbyId())
                        .orElseThrow(() -> new HobbyNotFoundException("취미가 조회되지 않음"))
                )
                .user(userRepository.findByUserEmail(userEmail)
                        .orElseThrow(() -> new UserNotFoundException("유저가 조회되지 않음"))
                )
                .build();

        // 활동 추가
        return activityRepository.save(activity);
    }
}
