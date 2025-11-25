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
    distance: number;
}
