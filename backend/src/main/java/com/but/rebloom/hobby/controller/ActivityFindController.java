package com.but.rebloom.hobby.controller;

import com.but.rebloom.common.dto.ApiResponse;
import com.but.rebloom.hobby.domain.Activity;
import com.but.rebloom.hobby.dto.response.FindActivityResponse;
import com.but.rebloom.hobby.usecase.DefaultActivityUseCase;
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
    // 서비스로직 DTO 종속성 방지를 위하여 제거
    @GetMapping("/id/{activityId}")
    public ResponseEntity<ApiResponse<FindActivityResponse>> findActivityByUserId(@PathVariable Long activityId) {
        Activity activity = defaultActivityUseCase.findActivityByActivityId(activityId);
        return ResponseEntity.ok(ApiResponse.success(FindActivityResponse.from(activity)));
    }

    // Activity 조회 - ActivityName
    @GetMapping("/name/{activityName}")
    public ResponseEntity<ApiResponse<FindActivityResponse>> findActivityByActivityName(
            @RequestHeader String token,
            @PathVariable String activityName
    ) {
        Activity activity = defaultActivityUseCase.findActivityByActivityName(token, activityName);
        return ResponseEntity.ok(ApiResponse.success(FindActivityResponse.from(activity)));
    }

    // Activity 조회
    @GetMapping("/normal")
    public ResponseEntity<ApiResponse<List<FindActivityResponse>>> findActivity(
            @RequestHeader String token
    ) {
        List<Activity> activities = defaultActivityUseCase.findAllActivity(token);

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
    public ResponseEntity<ApiResponse<List<FindActivityResponse>>> findActivityOrderByRecentActivityAsc(
            @RequestHeader String token
    ) {
        List<Activity> activities = defaultActivityUseCase.findActivityOrderByActivityRecentAsc(token);

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
    public ResponseEntity<ApiResponse<List<FindActivityResponse>>> findActivityOrderByRecentActivityDesc(
            @RequestHeader String token
    ) {
        List<Activity> activities = defaultActivityUseCase.findActivityOrderByActivityRecentDesc(token);

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
