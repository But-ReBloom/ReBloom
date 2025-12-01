package com.but.rebloom.domain.achievement.dto.response;

import com.but.rebloom.domain.achievement.domain.Achievement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetAchievementResponse {
    @NotNull(message = "업적 아이디는 필수 반환값 입니다.")
    private Long achievementId;
    @NotBlank(message = "업적 제목은 필수 반환값 입니다.")
    private String achievementTitle;
    @NotBlank(message = "업적 설명은 필수 반환값 입니다.")
    private String achievementDescription;
    @NotNull(message = "업적 보상 포인트는 필수 반환값 입니다.")
    private Integer achievementRewardPoint;
    @NotNull(message = "업적 티어 포인트는 필수 반환값 입니다.")
    private Integer achievementTierPoint;

    public static GetAchievementResponse from(Achievement achievement) {
        return GetAchievementResponse.builder()
                .achievementId(achievement.getAchievementId())
                .achievementTitle(achievement.getAchievementTitle())
                .achievementDescription(achievement.getAchievementDescription())
                .achievementRewardPoint(achievement.getAchievementRewardPoint())
                .achievementTierPoint(achievement.getAchievementRewardTierPoint())
                .build();
    }
}
