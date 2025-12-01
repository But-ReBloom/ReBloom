export type PostType = 'NORMAL' | 'NOTICE' | 'EVENT'; // Adjust based on backend enum
export type PostStatus = 'APPROVED' | 'PENDING' | 'REJECTED'; // Adjust based on backend enum

export interface CreatePostRequest {
    userId: string;
    channelId: number;
    postTitle: string;
    postContent: string;
    postImage?: string;
    postType?: PostType;
}

export interface SearchPostsRequest {
    keyword: string;
}

export interface UpdatePostRequest {
    postTitle?: string;
    postContent?: string;
    postImage?: string;
    postType?: PostType;
}

export interface CreatePostResponse {
    postId: number;
    userId: string;
    userTier: string;
    channelId: number;
    postTitle: string;
    postContent: string;
    postImage: string;
    postType: PostType;
    postStatus: PostStatus;
    postCreatedAt: string;
    viewers: number;
}

export interface FindPostResponse {
    posts: CreatePostResponse[];
}

// Keep existing type for compatibility if needed, or refactor
export type Post = CreatePostResponse;

