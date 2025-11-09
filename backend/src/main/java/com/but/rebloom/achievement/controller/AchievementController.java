package com.but.rebloom.achievement.controller;

import com.but.rebloom.achievement.domain.Achievement;
import com.but.rebloom.achievement.dto.response.GetAchievementResponse;
import com.but.rebloom.achievement.usecase.DefaultAchievementUseCase;
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
    public ResponseEntity<List<GetAchievementResponse>> findAllAchievements() {
        List<Achievement> response = defaultAchievementUseCase.findAllAchievements();
        return ResponseEntity.ok(
                response.stream()
                .map(GetAchievementResponse::from)
                .toList()
        );
    }

    // 업적 조회 - 업적 아이디
    @GetMapping("/id/{achievementId}")
    public ResponseEntity<GetAchievementResponse> finaAchievementById(@PathVariable Long achievementId) {
        Achievement response = defaultAchievementUseCase.findAchievementById(achievementId);
        return ResponseEntity.ok(GetAchievementResponse.from(response));
    }

    // 업적 조회 - 업적 제목
    @GetMapping("/title/{achievementTitle}")
    public ResponseEntity<GetAchievementResponse> finaAchievementByTitle(@PathVariable String achievementTitle) {
        Achievement response = defaultAchievementUseCase.findAchievementByTitle(achievementTitle);
        return ResponseEntity.ok(GetAchievementResponse.from(response));
    }
}
