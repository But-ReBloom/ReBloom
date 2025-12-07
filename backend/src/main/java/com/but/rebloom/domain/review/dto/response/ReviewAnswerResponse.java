package com.but.rebloom.domain.review.dto.response;

import com.but.rebloom.domain.auth.domain.User;
import com.but.rebloom.domain.hobby.domain.Hobby;
import com.but.rebloom.domain.review.domain.ActivityReview;
import com.but.rebloom.domain.review.domain.ActivityReviewResult;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReviewAnswerResponse {
    @NotNull
    private Double socialScore;

    @NotNull
    private Double learningScore;

    @NotNull
    private Double planningScore;

    @NotNull
    private Double focusScore;

    @NotNull
    private Double creativityScore;

    @NotBlank
    private String hobby1;

    @NotBlank
    private String hobby2;

    @NotBlank
    private String hobby3;

    public static ReviewAnswerResponse of(ActivityReviewResult result) {
        User user = result.getUser();
        List<Hobby> hobbies = result.getHobbies();

        return ReviewAnswerResponse.builder()
                .socialScore(user.getSocialScore())
                .learningScore(user.getLearningScore())
                .planningScore(user.getPlanningScore())
                .focusScore(user.getFocusScore())
                .creativityScore(user.getCreativityScore())
                .hobby1(hobbies.get(0).getHobbyName())
                .hobby2(hobbies.get(1).getHobbyName())
                .hobby3(hobbies.get(2).getHobbyName())
                .build();
    }
}
