package com.but.rebloom.achievement.controller;

import com.but.rebloom.achievement.domain.UserAchievement;
import com.but.rebloom.achievement.dto.request.EmailAndIdRequest;
import com.but.rebloom.achievement.dto.request.EmailAndTitleRequest;
import com.but.rebloom.achievement.dto.request.IdAndIdRequest;
import com.but.rebloom.achievement.dto.request.IdAndTitleRequest;
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

    // 전체 유저 업적 조회 - 유저 아이디
    @GetMapping("/id/{userId}")
    public ResponseEntity<List<GetUserAchievementResponse>> getUserAchievementsByUserId(@PathVariable String userId) {
        List<UserAchievement> response = defaultUserAchievementUseCase.finaAllUserAchievementsByUserId(userId);
        return ResponseEntity.ok(
                response.stream()
                        .map(GetUserAchievementResponse::from)
                        .toList()
        );
    }

    // 전체 유저 업적 조회 - 유저 이메일
    @GetMapping("/email/{userEmail}")
    public ResponseEntity<List<GetUserAchievementResponse>> getUserAchievementsByUserEmail(@PathVariable String userEmail) {
        List<UserAchievement> response = defaultUserAchievementUseCase.finaAllUserAchievementsByUserEmail(userEmail);
        return ResponseEntity.ok(
                response.stream()
                        .map(GetUserAchievementResponse::from)
                        .toList()
        );
    }

    // 유저 업적 조회 - (유저 이메일 + 업적 아이디)
    @GetMapping("/email-id")
    public ResponseEntity<GetUserAchievementResponse> getUserAchievementsByUserEmailAndAchievementId(EmailAndIdRequest request) {
        UserAchievement response = defaultUserAchievementUseCase.finaUserAchievementByUserEmailAndAchievementId(request);
        return ResponseEntity.ok(GetUserAchievementResponse.from(response));
    }

    // 유저 업적 조회 - (유저 이메일 + 업적 제목)
    @GetMapping("/email-title")
    public ResponseEntity<GetUserAchievementResponse> getUserAchievementsByUserEmailAndAchievementTitle(EmailAndTitleRequest request) {
        UserAchievement response = defaultUserAchievementUseCase.finaUserAchievementByUserEmailAndAchievementTitle(request);
        return ResponseEntity.ok(GetUserAchievementResponse.from(response));
    }

    // 유저 업적 조회 - (유저 이메일 + 업적 아이디)
    @GetMapping("/id-id")
    public ResponseEntity<GetUserAchievementResponse> getUserAchievementsByUserIdAndAchievementId(IdAndIdRequest request) {
        UserAchievement response = defaultUserAchievementUseCase.finaUserAchievementByUserIdAndAchievementId(request);
        return ResponseEntity.ok(GetUserAchievementResponse.from(response));
    }

    // 유저 업적 조회 - (유저 아이디 + 업적 제목)
    @GetMapping("/id-title")
    public ResponseEntity<GetUserAchievementResponse> getUserAchievementsByUserIdAndAchievementTitle(IdAndTitleRequest request) {
        UserAchievement response = defaultUserAchievementUseCase.finaUserAchievementByUserIdAndAchievementTitle(request);
        return ResponseEntity.ok(GetUserAchievementResponse.from(response));
    }
}
