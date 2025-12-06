export interface GetAchievementResponse {
    achievementId: number;
    achievementTitle: string;
    achievementDescription: string;
    achievementRewardPoint: number;
    achievementTierPoint: number;
}

export interface GetUserAchievementResponse {
    achievementId: number;
    userAchievementTitle: string;
    userAchievementDescription: string;
    userAchievementRewardPoint: number;
    userAchievementTierPoint: number;
    userAchievementProgress: number;
    userAchievementIsSuccess: boolean;
}
