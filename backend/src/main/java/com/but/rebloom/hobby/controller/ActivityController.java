package com.but.rebloom.hobby.controller;

import com.but.rebloom.hobby.domain.Activity;
import com.but.rebloom.hobby.dto.request.AddActivityRequest;
import com.but.rebloom.hobby.dto.response.AddActivityResponse;
import com.but.rebloom.hobby.usecase.DefaultActivityControlUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activity")
public class ActivityController {
    // 기본 Activity 조정 함수 호출
    private final DefaultActivityControlUseCase defaultActivityControlUseCase;

    // Activity 추가
    @PostMapping("/add")
    public ResponseEntity<AddActivityResponse> addActivity(@RequestBody AddActivityRequest request) {
        Activity activity = defaultActivityControlUseCase.addActivity(request);
        return ResponseEntity.ok(AddActivityResponse.from(activity));
    }
}
