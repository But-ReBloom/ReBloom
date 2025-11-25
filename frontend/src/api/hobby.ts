import { client } from "./client";
import type { 
    InitialTest, 
    UserAnswerRequest, 
    HobbyScoreResponse,
    AddActivityRequest,
    AddActivityResponse,
    FindActivityResponse
} from "../types/hobby";
import type { ApiResponse } from "../types/common";

export const hobbyApi = {
    getQuestions: () => 
        client("/hobby-test/questions", {
            method: "GET",
        }) as Promise<ApiResponse<InitialTest[]>>,

    recommendHobby: (data: UserAnswerRequest) => 
        client("/hobby-test/recommend", {
            method: "POST",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<HobbyScoreResponse[]>>,

    addActivity: (data: AddActivityRequest) =>
        client("/activity/add", {
            method: "POST",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<AddActivityResponse>>,

    findActivityById: (activityId: number) =>
        client(`/activity/find/id/${activityId}`, {
            method: "GET",
        }) as Promise<ApiResponse<FindActivityResponse>>,

    findActivityByName: (activityName: string) =>
        client(`/activity/find/name/${activityName}`, {
            method: "GET",
        }) as Promise<ApiResponse<FindActivityResponse>>,

    findAllActivities: () =>
        client("/activity/find/normal", {
            method: "GET",
        }) as Promise<ApiResponse<FindActivityResponse[]>>,

    findActivitiesByRecentAsc: () =>
        client("/activity/find/normal/recent/asc", {
            method: "GET",
        }) as Promise<ApiResponse<FindActivityResponse[]>>,

    findActivitiesByRecentDesc: () =>
        client("/activity/find/normal/recent/desc", {
            method: "GET",
        }) as Promise<ApiResponse<FindActivityResponse[]>>,
};
