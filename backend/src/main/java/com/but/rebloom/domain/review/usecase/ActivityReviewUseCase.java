package com.but.rebloom.domain.review.usecase;

import com.but.rebloom.domain.auth.repository.UserRepository;
import com.but.rebloom.domain.hobby.usecase.HobbyTestUseCase;
import com.but.rebloom.domain.review.dto.request.CreateReviewQuestionRequest;
import com.but.rebloom.domain.review.dto.response.CreateReviewQuestionResponse;
import com.but.rebloom.domain.review.repository.ActivityReviewRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityReviewUseCase {
    private final ActivityReviewRepository repository;
    private final UserRepository userRepository;
    private final HobbyTestUseCase hobbyTestUseCase;
    private OpenAiChatModel openAiChatModel;

    // 질문 5개 생성
    public CreateReviewQuestionResponse createReviewQuestion(CreateReviewQuestionRequest request) {
        String prompt = """
                 당신은 활동 리뷰 질문 생성기입니다.
                 다음 취미 활동을 기반으로 사용자의 취향 카테고리 별 평가할 질문을 생성하세요.
                
                취향 카테고리:
                - Society
                - Learning
                - Planning
                - Focus
                - Creativity
                
                각각의 카테고리에 대해 한 문장짜리 질문을 만들어주세요.
                """;

        String response = openAiChatModel.call(prompt);

        //파싱
        String[] lines = response.split("\n");

        return CreateReviewQuestionResponse.builder()
                .socialQuestion(lines[0])
                .learningQuestion(lines[1])
                .planningQuestion(lines[2])
                .focusQuestion(lines[3])
                .creativityQuestion(lines[4])
                .build();
    }
}