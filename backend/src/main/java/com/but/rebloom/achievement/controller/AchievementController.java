package com.but.rebloom.achievement.controller;

import com.but.rebloom.achievement.domain.Achievement;
import com.but.rebloom.achievement.dto.response.GetAchievement;
import com.but.rebloom.achievement.usecase.DefaultAchievementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/achievement")
public class AchievementController {
    // 로직 이용
    private final DefaultAchievementUseCase defaultAchievementUseCase;

    // 전제 업적 조회
    @GetMapping("/all")
    public ResponseEntity<List<GetAchievement>> findAllAchievements() {
        List<Achievement> response = defaultAchievementUseCase.findAllAchievements();
        return ResponseEntity.ok(
                response.stream()
                .map(GetAchievement::from)
                .toList()
        );
    }

    // 업적 조회 - 업적 아이디
    @GetMapping("/{achievement}")
    public ResponseEntity<GetAchievement> finaAchievement(@RequestParam Long achievementId) {
        Achievement response = defaultAchievementUseCase.findAchievementById(achievementId);
        return ResponseEntity.ok(GetAchievement.from(response));
    }

    // 업적 조회 - 업적 제목
    @GetMapping("/{achievementTitle}")
    public ResponseEntity<GetAchievement> finaAchievement(@RequestParam String achievementTitle) {
        Achievement response = defaultAchievementUseCase.findAchievementByTitle(achievementTitle);
        return ResponseEntity.ok(GetAchievement.from(response));
    }
}
