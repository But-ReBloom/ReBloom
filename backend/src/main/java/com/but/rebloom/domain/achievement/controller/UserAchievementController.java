package com.but.rebloom.domain.achievement.controller;

import com.but.rebloom.domain.achievement.domain.UserAchievement;
import com.but.rebloom.domain.achievement.dto.response.GetUserAchievementResponse;
import com.but.rebloom.domain.achievement.usecase.DefaultUserAchievementUseCase;
import com.but.rebloom.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-achievement")
public class UserAchievementController {
    // 함수 호출
    private final DefaultUserAchievementUseCase defaultUserAchievementUseCase;

    // 전체 유저 업적 조회 - 유저 아이디
    @GetMapping("/id")
    public ResponseEntity<ApiResponse<List<GetUserAchievementResponse>>> getUserAchievementsByUserId() {
        List<UserAchievement> response = defaultUserAchievementUseCase.finaAllUserAchievementsByUserId();
        return ResponseEntity.ok(
                ApiResponse.success(
                    response.stream()
                            .map(GetUserAchievementResponse::from)
                            .toList()
                )
        );
    }

    // 전체 유저 업적 조회 - 유저 이메일
    @GetMapping("/email")
    public ResponseEntity<ApiResponse<List<GetUserAchievementResponse>>> getUserAchievementsByUserEmail() {
        List<UserAchievement> response = defaultUserAchievementUseCase.finaAllUserAchievementsByUserEmail();
        return ResponseEntity.ok(
                ApiResponse.success(
                        response.stream()
                                .map(GetUserAchievementResponse::from)
                                .toList()
                )
        );
    }

    // 유저 업적 조회 - (유저 이메일 + 업적 아이디)
    @GetMapping("/id/{achievementId}")
    public ResponseEntity<ApiResponse<GetUserAchievementResponse>> getUserAchievementsByUserEmailAndAchievementId(
            @PathVariable Long achievementId
    ) {
        UserAchievement response = defaultUserAchievementUseCase
                .findUserAchievementByUserEmailAndAchievementId(achievementId);
        return ResponseEntity.ok(ApiResponse.success(GetUserAchievementResponse.from(response)));
    }
}
