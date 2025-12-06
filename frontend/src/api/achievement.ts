import { client } from "./client";
import type { 
    GetAchievementResponse,
    GetUserAchievementResponse
} from "../types/achievement";
import type { ApiResponse } from "../types/common";

export const achievementApi = {
    findAllAchievements: () => 
        client("/achievement/find/all", {
            method: "GET",
        }) as Promise<ApiResponse<GetAchievementResponse[]>>,

    findAchievementById: (achievementId: number) => 
        client(`/achievement/find/id/${achievementId}`, {
            method: "GET",
        }) as Promise<ApiResponse<GetAchievementResponse>>,

    getUserAchievementsByUserEmail: () => 
        client("/user-achievement/find/user-email", {
            method: "GET",
        }) as Promise<ApiResponse<GetUserAchievementResponse[]>>,

    getUserAchievementsByUserEmailAndAchievementId: (achievementId: number) => 
        client(`/user-achievement/find/achievement-id/${achievementId}`, {
            method: "GET",
        }) as Promise<ApiResponse<GetUserAchievementResponse>>,
};
