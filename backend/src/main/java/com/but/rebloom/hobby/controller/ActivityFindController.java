package com.but.rebloom.hobby.controller;

import com.but.rebloom.hobby.domain.Activity;
import com.but.rebloom.hobby.dto.response.FindActivityResponse;
import com.but.rebloom.hobby.usecase.DefaultActivityControlUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activity/find")
public class ActivityFindController {
    // 함수 호출
    private final DefaultActivityControlUseCase defaultActivityControlUseCase;

    // Activity 조회 - ActivityId
    // 서비스로직 DTO 종속성 방지를 위하여 제거
    @GetMapping("/id/{activityId}")
    public ResponseEntity<FindActivityResponse> findActivityByUserId(@PathVariable Long activityId) {
        Activity activity = defaultActivityControlUseCase.findActivityByActivityId(activityId);
        return ResponseEntity.ok(FindActivityResponse.from(activity));
    }

    // Activity 조회 - ActivityName
    @GetMapping("/name/{activityName}")
    public ResponseEntity<FindActivityResponse> findActivityByActivityName(@PathVariable String activityName) {
        Activity activity = defaultActivityControlUseCase.findActivityByActivityName(activityName);
        return ResponseEntity.ok(FindActivityResponse.from(activity));
    }

    // Activity 조회
    @GetMapping("/normal")
    public ResponseEntity<List<FindActivityResponse>> findActivity() {
        List<Activity> activities = defaultActivityControlUseCase.findActivity();

        // List<Activity> -> List<FindActivityResponse>로 변환
        return ResponseEntity.ok(
                activities.stream()
                .map(FindActivityResponse::from)
                .toList()
        );
    }

    // Activity 조회 - ActivityRecent(ASC)
    @GetMapping("/normal/recent/asc")
    public ResponseEntity<List<FindActivityResponse>> findActivityOrderByRecentActivityAsc() {
        List<Activity> activities = defaultActivityControlUseCase.findActivityOrderByActivityRecentAsc();

        // List<Activity> -> List<FindActivityResponse>로 변환
        return ResponseEntity.ok(
                activities.stream()
                        .map(FindActivityResponse::from)
                        .toList()
        );
    }

    // Activity 조회 - ActivityRecent(DESC)
    @GetMapping("/normal/recent/asc")
    public ResponseEntity<List<FindActivityResponse>> findActivityOrderByRecentActivityDesc() {
        List<Activity> activities = defaultActivityControlUseCase.findActivityOrderByActivityRecentDesc();

        // List<Activity> -> List<FindActivityResponse>로 변환
        return ResponseEntity.ok(
                activities.stream()
                        .map(FindActivityResponse::from)
                        .toList()
        );
    }
}
