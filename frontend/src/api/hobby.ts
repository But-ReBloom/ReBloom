import { client } from "./client";
import type { 
    InitialTest, 
    UserAnswerRequest, 
    HobbyScoreResponse 
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
};
