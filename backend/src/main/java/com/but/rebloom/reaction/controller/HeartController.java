package com.but.rebloom.reaction.controller;

import com.but.rebloom.reaction.domain.Heart;
import com.but.rebloom.reaction.dto.request.CreateHeartRequest;
import com.but.rebloom.reaction.dto.request.DeleteHeartRequest;
import com.but.rebloom.reaction.dto.response.CreateHeartResponse;
import com.but.rebloom.reaction.dto.response.FindHeartResponse;
import com.but.rebloom.reaction.usecase.HeartUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/heart")
public class HeartController {
    private final HeartUseCase heartUseCase;

    // 하트 생성 (좋아요 누르기)
    @PostMapping
    public ResponseEntity<CreateHeartResponse> createHeart(@Valid @RequestBody CreateHeartRequest request) {
        Heart heart = heartUseCase.createHeart(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateHeartResponse.from(heart));
    }

    // 특정 게시글의 하트 목록 조회
    @GetMapping("/post/{postId}")
    public ResponseEntity<FindHeartResponse> getHeartsByPost(@PathVariable Long postId) {
        List<Heart> hearts = heartUseCase.getHeartsByPost(postId);
        return ResponseEntity.ok(FindHeartResponse.from(hearts));
    }

    // 특정 유저가 누른 하트 목록 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<FindHeartResponse> getHeartsByUser(@PathVariable String userId) {
        List<Heart> hearts = heartUseCase.getHeartsByUser(userId);
        return ResponseEntity.ok(FindHeartResponse.from(hearts));
    }

    // 특정 게시글의 하트 수 조회
    @GetMapping("/post/{postId}/count")
    public ResponseEntity<Map<String, Long>> getHeartCount(@PathVariable Long postId) {
        long count = heartUseCase.getHeartCount(postId);
        Map<String, Long> response = new HashMap<>();
        response.put("count", count);
        return ResponseEntity.ok(response);
    }

    // 특정 유저가 특정 게시글에 하트를 눌렀는지 확인
    @GetMapping("/check")
    public ResponseEntity<Map<String, Boolean>> isHeartExists(
            @RequestParam String userId,
            @RequestParam Long postId) {
        boolean exists = heartUseCase.isHeartExists(userId, postId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isLiked", exists);
        return ResponseEntity.ok(response);
    }

    // 하트 취소 (좋아요 취소)
    @DeleteMapping
    public ResponseEntity<Void> deleteHeart(@Valid @RequestBody DeleteHeartRequest request) {
        heartUseCase.deleteHeart(request);
        return ResponseEntity.noContent().build();
    }
}
