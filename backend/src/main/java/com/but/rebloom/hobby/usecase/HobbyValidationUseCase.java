package com.but.rebloom.hobby.usecase;

import com.but.rebloom.common.exception.IllegalArgumentException;
import com.but.rebloom.common.usecase.ValidationUseCase;
import com.but.rebloom.hobby.exception.AlreadyUsingActivityException;
import com.but.rebloom.hobby.exception.WrongTimeStampException;
import com.but.rebloom.hobby.dto.request.AddActivityRequest;
import com.but.rebloom.hobby.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class HobbyValidationUseCase {
    // DI
    private final ValidationUseCase validationUseCase;
    // 디비 이용
    private final ActivityRepository activityRepository;

    // 널 값 확인 - 한 요소
    public <T> void checkNull(T element) {
        validationUseCase.checkNull(element);
    }

    // 널 값 확인 - Activity 추가
    public void checkNull(AddActivityRequest addActivityRequest) {
        String activityName = addActivityRequest.getActivityName();
        LocalDate activityStart = addActivityRequest.getActivityStart();
        LocalDate activityRecent = addActivityRequest.getActivityRecent();

        if (activityName.isEmpty() || activityStart == null || activityRecent == null) {
            throw new IllegalArgumentException("빈 값이 존재");
        }
    }

    // 활동 이름 확인
    public void checkActivityName(String activityName) {
        if (activityName.length() < 1 || activityName.length() > 20) {
            throw new IllegalArgumentException("길이가 잘못됨");
        }
    }

    // 시작 활동일 확인
    public void checkActivityStart(LocalDate activityStart) {
        // 시작일이 현재 날짜 이후일 경우
        if (activityStart.isAfter(LocalDate.now())) {
            throw new WrongTimeStampException("잘못된 타임스탬프");
        }

        // 모든 기준은 시작일로 하기에 최근 접속일 보다 이후가 아닌지 확인하는 예외처리는 존재하지 않는다.
    }

    // 최근 활동일 확인
    public void checkActivityRecent(LocalDate activityRecent, LocalDate activityStart) {
        // 최근 활동일이 현재 날짜 이후일 경우
        if (activityRecent.isAfter(LocalDate.now())) {
            throw new WrongTimeStampException("잘못된 타임스탬프");
        }

        // 최근 활동일이 시작일 보다 이전일 경우
        if (activityRecent.isBefore(activityStart)) {
            throw new WrongTimeStampException("잘못된 타임스탬프");
        }
    }

    // 이메일 확인
    public void checkUserEmail(String userEmail) {
        validationUseCase.checkUserEmail(userEmail);
    }

    // 존재하는 유저인지 확인
    public void checkNotExistAccountByUserEmail(String userEmail) {
        validationUseCase.checkNotExistAccountByUserEmail(userEmail);
    }

    // 해당 유저가 하고 있지 않은 활동인지 확인
    public void checkExistActivityByEmailAndActivityName(String email, String activityName) {
        if (activityRepository.findActivityByActivityNameAndUserEmail(email, activityName).isPresent()) {
            throw new AlreadyUsingActivityException("이미 활동중인 활동");
        }
    }
}
