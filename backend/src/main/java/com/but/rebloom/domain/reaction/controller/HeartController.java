package com.but.rebloom.domain.reaction.controller;

import com.but.rebloom.domain.post.domain.Post;
import com.but.rebloom.domain.reaction.dto.response.CheckHeartExistsResponse;
import com.but.rebloom.domain.reaction.dto.response.CreateHeartResponse;
import com.but.rebloom.domain.reaction.dto.response.FindHeartResponse;
import com.but.rebloom.domain.reaction.dto.response.GetHeartCountResponse;
import com.but.rebloom.global.dto.ApiResponse;
import com.but.rebloom.domain.reaction.domain.Heart;
import com.but.rebloom.domain.reaction.dto.request.CheckHeartExistsRequest;
import com.but.rebloom.domain.reaction.dto.request.CreateHeartRequest;
import com.but.rebloom.domain.reaction.dto.request.DeleteHeartRequest;
import com.but.rebloom.domain.reaction.usecase.HeartUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/heart")
public class HeartController {
    private final HeartUseCase heartUseCase;

    // 하트 생성 (좋아요 누르기)
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CreateHeartResponse>> createHeart(@Valid @RequestBody CreateHeartRequest request) {
        Heart heart = heartUseCase.createHeart(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(CreateHeartResponse.from(heart)));
    }

    // 특정 게시글의 하트 목록 조회
    @GetMapping("/find/post/{postId}")
    public ResponseEntity<ApiResponse<FindHeartResponse>> getHeartsByPost(@PathVariable Long postId) {
        List<Heart> hearts = heartUseCase.getHeartsByPost(postId);
        return ResponseEntity.ok(ApiResponse.success(FindHeartResponse.from(hearts)));
    }

    // 특정 유저가 누른 하트 목록 조회
    @GetMapping("/find/user/{userId}")
    public ResponseEntity<ApiResponse<FindHeartResponse>> getHeartsByUser(@PathVariable String userId) {
        List<Heart> hearts = heartUseCase.getHeartsByUser(userId);
        return ResponseEntity.ok(ApiResponse.success(FindHeartResponse.from(hearts)));
    }

    // 특정 게시글의 하트 수 조회
    @GetMapping("/find/post/{postId}/count")
    public ResponseEntity<ApiResponse<GetHeartCountResponse>> getHeartCount(@PathVariable Long postId) {
        Map<Post, Long> response = heartUseCase.getHeartCount(postId);
        return ResponseEntity.ok(ApiResponse.success(GetHeartCountResponse.from(response)));
    }

    // 특정 유저가 특정 게시글에 하트를 눌렀는지 확인
    @GetMapping("/find/check")
    public ResponseEntity<ApiResponse<CheckHeartExistsResponse>> checkHeartExists(@ModelAttribute CheckHeartExistsRequest request) {
        Map<Post, Boolean> response = heartUseCase.checkHeartExists(request);
        return ResponseEntity.ok(ApiResponse.success(CheckHeartExistsResponse.from(response)));
    }

    // 하트 취소 (좋아요 취소)
    @DeleteMapping("/remove")
    public ResponseEntity<ApiResponse<Void>> deleteHeart(@Valid @RequestBody DeleteHeartRequest request) {
        heartUseCase.deleteHeart(request);
        return ResponseEntity.ok(ApiResponse.success());
    }
}
