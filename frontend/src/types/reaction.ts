export interface HeartRequest {
    userId: string;
    postId: number;
}

export type CreateHeartRequest = HeartRequest;
export type CheckHeartExistsRequest = HeartRequest;
export type DeleteHeartRequest = HeartRequest;

export interface CreateHeartResponse {
    heartId: number;
    userId: string;
    userName: string;
    postId: number;
}

export interface FindHeartResponse {
    hearts: CreateHeartResponse[];
}

export interface CountResponse {
    count: number;
}

export type GetHeartCountResponse = CountResponse;

export interface CheckHeartExistsResponse {
    exists: boolean;
}

export interface CreateCommentRequest {
    userId: string;
    postId: number;
    commentContent: string;
}

export interface UpdateCommentRequest {
    commentContent: string;
}

export interface CreateCommentResponse {
    commentId: number;
    userId: string;
    userTier: string;
    userName: string;
    postId: number;
    commentContent: string;
    commentCreatedAt: string;
}

export interface FindCommentResponse {
    comments: CreateCommentResponse[];
}

export type GetCommentCountResponse = CountResponse;
