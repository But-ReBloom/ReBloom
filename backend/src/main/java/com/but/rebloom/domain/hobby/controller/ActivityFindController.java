package com.but.rebloom.domain.hobby.controller;

import com.but.rebloom.global.dto.ApiResponse;
import com.but.rebloom.domain.hobby.domain.Activity;
import com.but.rebloom.domain.hobby.dto.response.FindActivityResponse;
import com.but.rebloom.domain.hobby.usecase.DefaultActivityUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activity/find")
public class ActivityFindController {
    // 함수 호출
    private final DefaultActivityUseCase defaultActivityUseCase;

    // Activity 조회 - ActivityId
    @GetMapping("/activity-id/{activityId}")
    public ResponseEntity<ApiResponse<FindActivityResponse>> findActivityByUserId(
            @PathVariable Long activityId
    ) {
        Activity activity = defaultActivityUseCase.findActivityByActivityId(activityId);
        return ResponseEntity.ok(ApiResponse.success(FindActivityResponse.from(activity)));
    }

    // Activity 조회
    @GetMapping("/normal")
    public ResponseEntity<ApiResponse<List<FindActivityResponse>>> findActivity() {
        List<Activity> activities = defaultActivityUseCase.findAllActivity();

        // List<Activity> -> List<FindActivityResponse>로 변환
        return ResponseEntity.ok(
                ApiResponse.success(
                    activities.stream()
                    .map(FindActivityResponse::from)
                    .toList()
                )
        );
    }

    // Activity 조회 - ActivityRecent(ASC)
    @GetMapping("/normal/recent/asc")
    public ResponseEntity<ApiResponse<List<FindActivityResponse>>> findActivityOrderByRecentActivityAsc() {
        List<Activity> activities = defaultActivityUseCase.findActivityOrderByActivityRecentAsc();

        // List<Activity> -> List<FindActivityResponse>로 변환
        return ResponseEntity.ok(
                ApiResponse.success(
                    activities.stream()
                            .map(FindActivityResponse::from)
                            .toList()
                )
        );
    }

    // Activity 조회 - ActivityRecent(DESC)
    @GetMapping("/normal/recent/desc")
    public ResponseEntity<ApiResponse<List<FindActivityResponse>>> findActivityOrderByRecentActivityDesc() {
        List<Activity> activities = defaultActivityUseCase.findActivityOrderByActivityRecentDesc();

        // List<Activity> -> List<FindActivityResponse>로 변환
        return ResponseEntity.ok(
                ApiResponse.success(
                    activities.stream()
                            .map(FindActivityResponse::from)
                            .toList()
                )
        );
    }
}
