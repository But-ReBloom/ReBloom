import { client } from "./client";
import type { 
    CreatePostRequest,
    CreatePostResponse,
    SearchPostsRequest,
    FindPostResponse,
    UpdatePostRequest,
    PostStatus
} from "../types/PostTypes";
import type { ApiResponse } from "../types/common";

export const postApi = {
    createPost: (data: CreatePostRequest) => 
        client("/post/create", {
            method: "POST",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<CreatePostResponse>>,

    findPost: (postId: number) => 
        client(`/post/find/post/${postId}`, {
            method: "GET",
        }) as Promise<ApiResponse<CreatePostResponse>>,

    getPostsByChannel: (channelId: number) => 
        client(`/post/find/channel/${channelId}`, {
            method: "GET",
        }) as Promise<ApiResponse<FindPostResponse>>,

    getPostsByUser: (userId: string) => 
        client(`/post/find/user/${userId}`, {
            method: "GET",
        }) as Promise<ApiResponse<FindPostResponse>>,

    getPopularPosts: () => 
        client("/post/find/popular", {
            method: "GET",
        }) as Promise<ApiResponse<FindPostResponse>>,

    searchPosts: (data: SearchPostsRequest) => 
        client(`/post/find/keyword?keyword=${data.keyword}`, {
            method: "GET",
        }) as Promise<ApiResponse<FindPostResponse>>,

    updatePost: (postId: number, data: UpdatePostRequest) => 
        client(`/post/update/post/${postId}`, {
            method: "PUT",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<CreatePostResponse>>,

    deletePost: (postId: number) => 
        client(`/post/delete/post/${postId}`, {
            method: "DELETE",
        }) as Promise<ApiResponse<void>>,

    deletePostByAdmin: (postId: number) => 
        client(`/post/admin/post/${postId}`, {
            method: "DELETE",
        }) as Promise<ApiResponse<void>>,

    getPostsByStatus: (status: PostStatus) => 
        client(`/post/admin/status/${status}`, {
            method: "GET",
        }) as Promise<ApiResponse<FindPostResponse>>,

    approvePost: (postId: number) => 
        client(`/post/admin/post/${postId}/approve`, {
            method: "PATCH",
        }) as Promise<ApiResponse<CreatePostResponse>>,

    rejectPost: (postId: number) => 
        client(`/post/admin/post/${postId}/reject`, {
            method: "PATCH",
        }) as Promise<ApiResponse<CreatePostResponse>>,
};
