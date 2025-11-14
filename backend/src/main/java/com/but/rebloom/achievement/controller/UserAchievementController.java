package com.but.rebloom.achievement.controller;

import com.but.rebloom.achievement.domain.UserAchievement;
import com.but.rebloom.achievement.dto.response.GetUserAchievementResponse;
import com.but.rebloom.achievement.usecase.DefaultUserAchievementUseCase;
import com.but.rebloom.common.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse<GetUserAchievementResponse>> getUserAchievementsByUserEmailAndAchievementId(@PathVariable Long achievementId) {
        UserAchievement response = defaultUserAchievementUseCase.finaUserAchievementByUserEmailAndAchievementId(achievementId);
        return ResponseEntity.ok(ApiResponse.success(GetUserAchievementResponse.from(response)));
    }

    // 유저 업적 조회 - (유저 이메일 + 업적 제목)
    @GetMapping("/title/{achievementTitle}")
    public ResponseEntity<ApiResponse<GetUserAchievementResponse>> getUserAchievementsByUserEmailAndAchievementTitle(@PathVariable String achievementTitle) {
        UserAchievement response = defaultUserAchievementUseCase.finaUserAchievementByUserEmailAndAchievementTitle(achievementTitle);
        return ResponseEntity.ok(ApiResponse.success(GetUserAchievementResponse.from(response)));
    }
}
