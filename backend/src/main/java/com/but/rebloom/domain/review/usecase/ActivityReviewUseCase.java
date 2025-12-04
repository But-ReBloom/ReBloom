package com.but.rebloom.domain.review.usecase;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.auth.usecase.FindCurrentUserUseCase;
import com.but.rebloom.domain.hobby.domain.Hobby;
import com.but.rebloom.domain.hobby.exception.HobbyNotFoundException;
import com.but.rebloom.domain.hobby.repository.HobbyRepository;
import com.but.rebloom.domain.hobby.usecase.HobbyTestUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityReviewUseCase {
    private final OpenAIUseCase openAIUseCase;
    private final UserRepository userRepository;
    private final ActivityReviewRepository activityRepository;
    private final HobbyRepository hobbyRepository;
    private final HobbyTestUseCase hobbyTestUseCase;
    private final FindCurrentUserUseCase findCurrentUserUseCase;
    private final ObjectMapper objectMapper;

    // AI 리뷰 생성
    public ReviewQuestionResponse generateReviewQuestion(Long hobbyId) {
        User currentUser = findCurrentUserUseCase.getCurrentUser();

        Hobby hobby = hobbyRepository.findById(hobbyId)
                .orElseThrow(() -> new HobbyNotFoundException("취미를 찾을 수 없습니다."));

        List<Map<String, String>> aiQuestions = openAIUseCase.generateReviewQuestions(hobby.getHobbyName());

        List<ReviewQuestionDto> questions = aiQuestions.stream()
                .map(q -> ReviewQuestionDto.builder()
                        .question(q.get("question"))
                        .targetDimension(q.get("dimension"))
                        .build())
                .collect(Collectors.toList());

        return ReviewQuestionResponse.builder()
                .hobbyId(hobbyId)
                .hobbyName(hobby.getHobbyName())
                .questions(questions)
                .build();
    }
}
