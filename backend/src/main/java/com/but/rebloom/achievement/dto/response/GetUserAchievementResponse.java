package com.but.rebloom.achievement.dto.response;

import com.but.rebloom.achievement.domain.Achievement;
import com.but.rebloom.achievement.domain.UserAchievement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetUserAchievementResponse {
    @NotNull
    private Boolean success;
    @NotBlank(message = "업적 제목은 필수 반환값 입니다.")
    private String userAchievementTitle;
    @NotBlank(message = "업적 설명은 필수 반환값 입니다.")
    private String userAchievementDescription;
    @NotNull(message = "업적 보상 포인트는 필수 반환값 입니다.")
    private Integer userAchievementRewardPoint;
    @NotNull(message = "업적 티어 포인트는 필수 반환값 입니다.")
    private Integer userAchievementTierPoint;
    @NotNull
    private Float userAchievementProgress;
    @NotNull
    private Boolean userAchievementIsSuccess;

    public static GetUserAchievementResponse from(UserAchievement userAchievement) {
        Achievement achievement = userAchievement.getAchievement();

        return GetUserAchievementResponse.builder()
                .success(true)
                .userAchievementTitle(userAchievement.getAchievementTitle())
                .userAchievementDescription(achievement.getAchievementDescription())
                .userAchievementRewardPoint(achievement.getAchievementRewardPoint())
                .userAchievementTierPoint(achievement.getAchievementRewardTierPoint())
                .userAchievementProgress(userAchievement.getProgress())
                .userAchievementIsSuccess(userAchievement.getIsSuccess())
                .build();
    }
}
