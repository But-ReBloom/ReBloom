package com.but.rebloom.hobby.usecase;

import com.but.rebloom.common.exception.ActivityNotFoundException;
import com.but.rebloom.hobby.domain.Activity;
import com.but.rebloom.hobby.dto.request.FindActivityRequest;
import com.but.rebloom.hobby.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultActivityControlUseCase {
    private final ActivityRepository activityRepository;

    public Activity findActivity(FindActivityRequest request) {
        Optional<Activity> optionalActivity = activityRepository.findActivityByActivityId(request.getActivityId());
        if (optionalActivity.isPresent()) {
            return optionalActivity.get();
        }
        throw new ActivityNotFoundException("활동 조회 실패");
    }
}
