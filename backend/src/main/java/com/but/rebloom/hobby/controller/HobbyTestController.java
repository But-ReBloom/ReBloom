package com.but.rebloom.hobby.controller;

import com.but.rebloom.hobby.domain.HobbyWeight;
import com.but.rebloom.hobby.domain.InitialTest;
import com.but.rebloom.hobby.dto.request.UserAnswerRequest;
import com.but.rebloom.hobby.dto.response.HobbyScoreResponse;
import com.but.rebloom.hobby.usecase.HobbyTestUseCase;
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
    public ResponseEntity<List<InitialTest>> getQuestions() {
        List<InitialTest> questions = hobbyTestUseCase.getQuestions();
        return ResponseEntity.ok(questions);
    }

     // 활동 추천 로직
    @PostMapping("/recommend")
    public ResponseEntity<List<HobbyScoreResponse>> recommendHobby(@RequestBody List<UserAnswerRequest> answers) {
        List<Map<HobbyWeight, Double>> hobbies = hobbyTestUseCase.findUserHobbies(answers);
        return ResponseEntity.ok(
                hobbies.stream()
                        .map(m -> HobbyScoreResponse.from(
                                m.keySet().iterator().next(),
                                m.values().iterator().next()
                        ))
                        .toList()
        );
    }
}