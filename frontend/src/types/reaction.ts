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
    totalCount: number;
}

export interface CountResponse {
    count: number;
}

export interface GetHeartCountResponse {
    postTitle: string;
    heartCount: number;
}

export interface CheckHeartExistsResponse {
    postTitle: string;
    isExists: boolean;
}

export interface CreateCommentRequest {
    userId: string;
    postId: number;
    commentContent: string;
}

export interface UpdateCommentRequest {
    commentId: number;
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
    totalCount: number;
}

export interface GetCommentCountResponse {
    postTitle: string;
    userId: string;
    userTier: string;
    postCount: number;
}
