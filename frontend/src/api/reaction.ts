import { client } from "./client";
import type { 
    CreateHeartRequest,
    CreateHeartResponse,
    CheckHeartExistsRequest,
    CheckHeartExistsResponse,
    DeleteHeartRequest,
    FindHeartResponse,
    GetHeartCountResponse,
    CreateCommentRequest,
    CreateCommentResponse,
    UpdateCommentRequest,
    FindCommentResponse,
    GetCommentCountResponse
} from "../types/reaction";
import type { ApiResponse } from "../types/common";

export const reactionApi = {
    // Heart
    createHeart: (data: CreateHeartRequest) => 
        client("/heart/add", {
            method: "POST",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<CreateHeartResponse>>,

    getHeartsByPost: (postId: number) => 
        client(`/heart/find/post/${postId}`, {
            method: "GET",
        }) as Promise<ApiResponse<FindHeartResponse>>,

    getHeartsByUser: (userId: string) => 
        client(`/heart/find/user/${userId}`, {
            method: "GET",
        }) as Promise<ApiResponse<FindHeartResponse>>,

    getHeartCount: (postId: number) => 
        client(`/heart/find/post/${postId}/count`, {
            method: "GET",
        }) as Promise<ApiResponse<GetHeartCountResponse>>,

    checkHeartExists: (data: CheckHeartExistsRequest) => 
        client(`/heart/find/check?userId=${data.userId}&postId=${data.postId}`, {
            method: "GET",
        }) as Promise<ApiResponse<CheckHeartExistsResponse>>,

    deleteHeart: (data: DeleteHeartRequest) => 
        client("/heart/remove", {
            method: "DELETE",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<void>>,

    // Comment
    createComment: (data: CreateCommentRequest) => 
        client("/comment/create", {
            method: "POST",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<CreateCommentResponse>>,

    getComment: (commentId: number) => 
        client(`/comment/find/comment/${commentId}`, {
            method: "GET",
        }) as Promise<ApiResponse<CreateCommentResponse>>,

    getCommentsByPost: (postId: number) => 
        client(`/comment/find/post/${postId}`, {
            method: "GET",
        }) as Promise<ApiResponse<FindCommentResponse>>,

    getCommentsByUser: (userId: string) => 
        client(`/comment/find/user/${userId}`, {
            method: "GET",
        }) as Promise<ApiResponse<FindCommentResponse>>,

    getCommentCount: (postId: number) => 
        client(`/comment/find/post/${postId}/count`, {
            method: "GET",
        }) as Promise<ApiResponse<GetCommentCountResponse>>,

    updateComment: (commentId: number, data: UpdateCommentRequest) => 
        client(`/comment/update/${commentId}`, {
            method: "PUT",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<CreateCommentResponse>>,

    deleteComment: (commentId: number, userId: string) => 
        client(`/comment/delete/${commentId}?userId=${userId}`, {
            method: "DELETE",
        }) as Promise<ApiResponse<void>>,

    deleteCommentByAdmin: (commentId: number) => 
        client(`/comment/admin/${commentId}`, {
            method: "DELETE",
        }) as Promise<ApiResponse<void>>,
};
