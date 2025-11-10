package com.but.rebloom.achievement.controller;

import com.but.rebloom.achievement.domain.UserAchievement;
import com.but.rebloom.achievement.dto.response.GetUserAchievementResponse;
import com.but.rebloom.achievement.usecase.DefaultUserAchievementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-achievement")
public class UserAchievementController {
    // 함수 호출
    private final DefaultUserAchievementUseCase defaultUserAchievementUseCase;

    // 유저 업적 조회 - 유저 아이디
    @GetMapping("/id/{userId}")
    public ResponseEntity<List<GetUserAchievementResponse>> getUserAchievementsByUserId(@PathVariable String userId) {
        List<UserAchievement> response = defaultUserAchievementUseCase.finaAllUserAchievementsByUserId(userId);
        return ResponseEntity.ok(
                response.stream()
                        .map(GetUserAchievementResponse::from)
                        .toList()
        );
    }

    // 유저 업적 조회 - 유저 이메일
    @GetMapping("/email/{userEmail}")
    public ResponseEntity<List<GetUserAchievementResponse>> getUserAchievementsByUserEmail(@PathVariable String userEmail) {
        List<UserAchievement> response = defaultUserAchievementUseCase.finaAllUserAchievementsByUserEmail(userEmail);
        return ResponseEntity.ok(
                response.stream()
                        .map(GetUserAchievementResponse::from)
                        .toList()
        );
    }
}
