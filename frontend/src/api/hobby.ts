import { client } from "./client";
import type { 
    InitialTest, 
    UserAnswerRequest, 
    HobbyTestResponse,
    AddActivityRequest,
    AddActivityResponse,
    FindActivityResponse,
    GetAllHobbyResponse,
    GetHobbyResponse,
    CreateReviewQuestionRequest,
    CreateReviewQuestionResponse,
    ReviewAnswerRequest,
    ReviewAnswerResponse
} from "../types/hobby";
import type { ApiResponse } from "../types/common";

export const hobbyApi = {
    getQuestions: () => 
        client("/hobby-test/get/questions", {
            method: "GET",
        }) as Promise<ApiResponse<InitialTest[]>>,

    recommendHobby: (data: UserAnswerRequest) => 
        client("/hobby-test/recommend", {
            method: "POST",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<HobbyTestResponse>>,

    addActivity: (data: AddActivityRequest) =>
        client("/activity/add", {
            method: "POST",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<AddActivityResponse>>,

    findActivityById: (activityId: number) =>
        client(`/activity/find/activity-id/${activityId}`, {
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

    findAllHobbies: () =>
        client("/hobby/find/all", {
            method: "GET",
        }) as Promise<ApiResponse<GetAllHobbyResponse>>,

    findHobbyById: (hobbyId: number) =>
        client(`/hobby/find/${hobbyId}`, {
            method: "GET",
        }) as Promise<ApiResponse<GetHobbyResponse>>,

    createReviewQuestion: (data: CreateReviewQuestionRequest) =>
        client("/activity-review/create", {
            method: "POST",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<CreateReviewQuestionResponse>>,

    answerReview: (data: ReviewAnswerRequest) =>
        client("/activity-review/answer", {
            method: "POST",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<ReviewAnswerResponse>>,
};
