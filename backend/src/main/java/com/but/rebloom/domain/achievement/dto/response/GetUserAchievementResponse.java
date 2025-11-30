package com.but.rebloom.domain.achievement.dto.response;

import com.but.rebloom.domain.achievement.domain.Achievement;
import com.but.rebloom.domain.achievement.domain.UserAchievement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetUserAchievementResponse {
    @NotNull(message = "업적 아이디는 필수 반환값 입니다.")
    private Long achievementId;
    @NotBlank(message = "유저 업적 제목은 필수 반환값 입니다.")
    private String userAchievementTitle;
    @NotBlank(message = "유저 업적 설명은 필수 반환값 입니다.")
    private String userAchievementDescription;
    @NotNull(message = "유저 업적 보상 포인트는 필수 반환값 입니다.")
    private Integer userAchievementRewardPoint;
    @NotNull(message = "유저 업적 티어 포인트는 필수 반환값 입니다.")
    private Integer userAchievementTierPoint;
    @NotNull(message = "유저 업적 진행도는 필수 반환값 입니다.")
    private Float userAchievementProgress;
    @NotNull(message = "유저 업적 성공 여부는 필수 반환값 입니다.")
    private Boolean userAchievementIsSuccess;

    public static GetUserAchievementResponse from(UserAchievement userAchievement) {
        Achievement achievement = userAchievement.getAchievement();

        return GetUserAchievementResponse.builder()
                .achievementId(userAchievement.getAchievementId())
                .userAchievementTitle(userAchievement.getAchievement().getAchievementTitle())
                .userAchievementDescription(achievement.getAchievementDescription())
                .userAchievementRewardPoint(achievement.getAchievementRewardPoint())
                .userAchievementTierPoint(achievement.getAchievementRewardTierPoint())
                .userAchievementProgress(userAchievement.getUserAchievementProgress())
                .userAchievementIsSuccess(userAchievement.getIsSuccess())
                .build();
    }
}
