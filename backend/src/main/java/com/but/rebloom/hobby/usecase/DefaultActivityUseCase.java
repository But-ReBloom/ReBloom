package com.but.rebloom.hobby.usecase;

import com.but.rebloom.auth.domain.User;
import com.but.rebloom.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.hobby.exception.ActivityNotFoundException;
import com.but.rebloom.hobby.domain.Activity;
import com.but.rebloom.hobby.dto.request.AddActivityRequest;
import com.but.rebloom.hobby.repository.ActivityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    // Activity 조회 - ActivityId
    public Activity findActivityByActivityId(Long activityId) {
        return activityRepository.findByActivityId(activityId)
                .orElseThrow(() -> new ActivityNotFoundException("활동 조회 실패"));
    }

    // Activity 조회 - ActivityName
    public Activity findActivityByActivityName(String activityName) {
        String userEmail = findCurrentUserUseCase.getCurrentUser().getUserEmail();

        return activityRepository.findByActivityNameAndUserEmail(activityName, userEmail)
                .orElseThrow(() -> new ActivityNotFoundException("활동 조회 실패"));
    }

    // 전체 Activity 조회
    public List<Activity> findAllActivity() {
        String userEmail = findCurrentUserUseCase.getCurrentUser().getUserEmail();

        return activityRepository.findByUserEmail(userEmail);
    }

    // Activity 조회 - ActivityRecent(ASC)
    public List<Activity> findActivityOrderByActivityRecentAsc() {
        User user = findCurrentUserUseCase.getCurrentUser();
        String userEmail = user.getUserEmail();

        return activityRepository.findByUserEmailOrderByActivityRecentAsc(userEmail);
    }

    // Activity 조회 - ActivityRecent(DESC)
    public List<Activity> findActivityOrderByActivityRecentDesc() {
        User user = findCurrentUserUseCase.getCurrentUser();
        String userEmail = user.getUserEmail();

        return activityRepository.findByUserEmailOrderByActivityRecentDesc(userEmail);
    }

    // Activity 추가
    @Transactional
    public Activity addActivity(AddActivityRequest request) {
        String activityName = request.getActivityName();
        LocalDate activityStart = request.getActivityStart();
        LocalDate activityRecent = request.getActivityRecent();
        String userEmail = request.getUserEmail();

        // 각 요소별 예외처리
        hobbyValidationUseCase.checkActivityName(activityName);
        hobbyValidationUseCase.checkActivityStart(activityStart);
        hobbyValidationUseCase.checkActivityRecent(activityRecent, activityStart);
        hobbyValidationUseCase.checkUserEmail(userEmail);

        // 존재하지 않는 유저인지 확인
        hobbyValidationUseCase.checkNotExistAccountByUserEmail(userEmail);

        // 이미 활동중인 활동인지 확인
        hobbyValidationUseCase.checkExistActivityByEmailAndActivityName(userEmail, activityName);

        // 활동 생성
        Activity activity = Activity.builder()
                .activityName(activityName)
                .activityStart(activityStart)
                .activityRecent(activityRecent)
                .userEmail(userEmail)
                .build();

        // 활동 추가
        return activityRepository.save(activity);
    }
}
