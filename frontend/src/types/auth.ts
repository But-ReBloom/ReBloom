export type Provider = 'SELF' | 'GOOGLE' | 'DODAM';

export interface LoginRequest {
    userEmail: string;
    userPassword: string;
    userProvider: Provider;
}

export interface SignupRequest {
    userEmail: string;
    userId: string;
    userPassword: string;
    userName: string;
    userProvider: Provider;
}

export interface LoginResponse {
    userEmail: string;
    userProvider: Provider;
    token: string;
}

export interface SignupResponse {
    userId: string;
    userEmail: string;
    userProvider: Provider;
}

export interface SendVerificationEmailRequest {
    email: string;
}

export interface VerifyCodeRequest {
    email: string;
    code: string;
}

export interface GoogleLoginAuthorizeCodeRequest {
    code: string;
    redirectUri: string;
}

export interface UpdateUserIdRequest {
    updateUserId: string;
}

export interface UpdateUserPasswordRequest {
    updateUserPassword: string;
}

export interface FindEmailRequest {
    userId: string;
    userPassword: string;
}

export interface FindIdRequest {
    userEmail: string;
    userPassword: string;
}

export interface ChangeActivityRequest {
    activityId: number;
}

export interface GoogleUserInfoResponse {
    userEmail: string;
    userName: string;
    token: string;
}

export interface UpdateIdResponse {
    userId: string;
    userEmail: string;
}

export interface GetUserEmailResponse {
    userEmail: string;
}

export interface GetUserIdResponse {
    userId: string;
}

export interface FindUserInfoResponse {
    userId: string;
    userEmail: string;
    userName: string;
    userProvider: Provider;
    userActivityId: number;
}

export interface ChangeActivityResponse {
    userId: string;
    activityId: number;
}
