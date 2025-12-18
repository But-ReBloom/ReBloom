package com.but.rebloom.domain.hobby.controller;

import com.but.rebloom.global.dto.ApiResponse;
import com.but.rebloom.domain.hobby.domain.Activity;
import com.but.rebloom.domain.hobby.dto.request.AddActivityRequest;
import com.but.rebloom.domain.hobby.dto.response.AddActivityResponse;
import com.but.rebloom.domain.hobby.usecase.DefaultActivityUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activity")
public class ActivityController {
    // 기본 Activity 조정 함수 호출
    private final DefaultActivityUseCase defaultActivityUseCase;

    // Activity 추가
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<AddActivityResponse>> addActivity(@RequestBody AddActivityRequest request) {
        Activity activity = defaultActivityUseCase.addActivity(request);
        return ResponseEntity.ok(ApiResponse.success(AddActivityResponse.from(activity)));
    }

    // Activity 초기화
    @DeleteMapping("/reset")
    public ResponseEntity<ApiResponse<Void>> resetActivity() {
        defaultActivityUseCase.resetActivity();
        return ResponseEntity.ok(ApiResponse.success());
    }
}
