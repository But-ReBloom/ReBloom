package com.but.rebloom.achievement.dto.response;

import com.but.rebloom.achievement.domain.Achievement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetAchievement {
    @NotNull
    private Boolean success;
    @NotBlank(message = "업적 제목은 필수 반환값 입니다.")
    private String achievementTitle;
    @NotBlank(message = "업적 설명은 필수 반환값 입니다.")
    private String achievementDescription;
    @NotNull(message = "업적 보상 포인트는 필수 반환값 입니다.")
    private Integer achievementRewardPoint;
    @NotNull(message = "업적 티어 포인트는 필수 반환값 입니다.")
    private Integer achievementTierPoint;

    public static GetAchievement from(Achievement achievement) {
        return GetAchievement.builder()
                .success(true)
                .achievementTitle(achievement.getAchievementTitle())
                .achievementDescription(achievement.getAchievementDescription())
                .achievementRewardPoint(achievement.getAchievementRewardPoint())
                .achievementTierPoint(achievement.getAchievementTierPoint())
                .build();
    }
}
