export interface InitialTest {
    initialTestId: number;
    initialTestSetNumber: number;
    initialTestCategory: string;
    initialTestQuestion: string;
    initialTestSocialWeight: number;
    initialTestLearningWeight: number;
    initialTestPlanningWeight: number;
    initialTestFocusWeight: number;
    initialTestCreativityWeight: number;
}

export interface UserAnswerRequest {
    socialScore: number;
    learningScore: number;
    planningScore: number;
    focusScore: number;
    creativityScore: number;
}

export interface HobbyScoreResponse {
    hobbyName: string;
    hobbyId?: number; 
    distance: number;
}

export interface ChannelResponse {
    channelName: string;
}

export interface HobbyTestResponse {
    hobbyScores: HobbyScoreResponse[];
    channels: ChannelResponse[];
}

export interface AddActivityRequest {
    hobbyId: number;
    userEmail: string;
}

export interface AddActivityResponse {
    activityName: string;
    activityStart: string;
    activityRecent: string;
    userEmail: string;
}

export interface FindActivityResponse {
    activityName: string;
    activityStart: string;
    activityRecent: string;
}

export interface GetHobbyResponse {
    hobbyId: number;
    hobbyName: string;
    hobbyWeightSocial: number;
    hobbyWeightLearning: number;
    hobbyWeightPlanning: number;
    hobbyWeightFocus: number;
    hobbyWeightCreativity: number;
}

export interface GetAllHobbyResponse {
    hobbies: GetHobbyResponse[];
}

export interface CreateReviewQuestionRequest {
    hobbyId: number;
}

export interface CreateReviewQuestionResponse {
    reviewQuestion: string;
}

export interface ReviewAnswerRequest {
    activityReviewId: number;
    userEmail: string;
    hobbyId: number;
    socialAnswer: string;
    learningAnswer: string;
    planningAnswer: string;
    focusAnswer: string;
    creativityAnswer: string;
}

export interface ReviewAnswerResponse {
    socialScore: number;
    learningScore: number;
    planningScore: number;
    focusScore: number;
    creativityScore: number;
    hobby1: string;
    hobby2: string;
    hobby3: string;
}

export interface FindActivityResponse {
    activityId: number;
    activityName: string;
    activityStart: string;
    activityRecent: string;
    linkedHobbyId: number;
    linkedHobbyName: string;
}

