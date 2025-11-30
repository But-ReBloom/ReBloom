package com.but.rebloom.domain.achievement.controller;

import com.but.rebloom.domain.achievement.domain.Achievement;
import com.but.rebloom.domain.achievement.dto.response.GetAchievementResponse;
import com.but.rebloom.domain.achievement.usecase.DefaultAchievementUseCase;
import com.but.rebloom.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/achievement")
public class AchievementController {
    // 로직 이용
    private final DefaultAchievementUseCase defaultAchievementUseCase;

    // 전제 업적 조회
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<GetAchievementResponse>>> findAllAchievements() {
        List<Achievement> response = defaultAchievementUseCase.findAllAchievements();
        return ResponseEntity.ok(
                ApiResponse.success(
                    response.stream()
                    .map(GetAchievementResponse::from)
                    .toList()
                )
        );
    }

    // 업적 조회 - 업적 아이디
    @GetMapping("/id/{achievementId}")
    public ResponseEntity<ApiResponse<GetAchievementResponse>> findAchievementById(@PathVariable Long achievementId) {
        Achievement response = defaultAchievementUseCase.findAchievementById(achievementId);
        return ResponseEntity.ok(ApiResponse.success(GetAchievementResponse.from(response)));
    }

    // 업적 조회 - 업적 제목
    @GetMapping("/title/{achievementTitle}")
    public ResponseEntity<ApiResponse<GetAchievementResponse>> findAchievementByTitle(@PathVariable String achievementTitle) {
        Achievement response = defaultAchievementUseCase.findAchievementByTitle(achievementTitle);
        return ResponseEntity.ok(ApiResponse.success(GetAchievementResponse.from(response)));
    }
}
