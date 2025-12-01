export interface CreateHeartRequest {
    userId: string;
    postId: number;
}

export interface CheckHeartExistsRequest {
    userId: string;
    postId: number;
}

export interface DeleteHeartRequest {
    userId: string;
    postId: number;
}

export interface CreateHeartResponse {
    heartId: number;
    userId: string;
    userName: string;
    postId: number;
}

export interface FindHeartResponse {
    hearts: CreateHeartResponse[];
}

export interface GetHeartCountResponse {
    count: number;
}

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

export interface GetCommentCountResponse {
    count: number;
}
