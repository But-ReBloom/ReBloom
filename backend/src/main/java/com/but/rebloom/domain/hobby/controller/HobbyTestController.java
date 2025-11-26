package com.but.rebloom.domain.hobby.controller;

import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.global.dto.ApiResponse;
import com.but.rebloom.domain.hobby.domain.HobbyScore;
import com.but.rebloom.domain.hobby.domain.InitialTest;
import com.but.rebloom.domain.hobby.dto.request.UserAnswerRequest;
import com.but.rebloom.domain.hobby.dto.response.HobbyTestResponse;
import com.but.rebloom.domain.hobby.usecase.HobbyTestUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hobby-test")
@RequiredArgsConstructor
public class HobbyTestController {
    // 함수 호출
    private final HobbyTestUseCase hobbyTestUseCase;

    // 질문지 조회
    // dto 의존성 제거
    @GetMapping("/questions")
    public ResponseEntity<ApiResponse<List<InitialTest>>> getQuestions() {
        List<InitialTest> questions = hobbyTestUseCase.getQuestions();
        return ResponseEntity.ok(ApiResponse.success(questions));
    }

     // 활동, 채널 추천 로직
     @PostMapping("/recommend")
     public ResponseEntity<ApiResponse<HobbyTestResponse>> recommendHobby(@RequestBody UserAnswerRequest answers) {
         Map<List<HobbyScore>, List<Channel>> responses = hobbyTestUseCase.findUserHobbies(answers);
         return ResponseEntity.ok(ApiResponse.success(HobbyTestResponse.from(responses)));
     }
}