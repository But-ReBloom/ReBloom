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

export type VerificationPurpose = 'SIGN_UP' | 'UPDATE_INFO';

export interface SendVerificationEmailRequest {
    userEmail: string;
    purpose: VerificationPurpose;
}

export interface VerifyCodeRequest {
    userEmail: string;
    code: string;
    purpose: VerificationPurpose;
}

export interface GoogleLoginAuthorizeCodeRequest {
    authorizationCode: string;
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
    id: string;
    email: string;
    name: string;
    accessToken: string;
    provider: Provider;
}

export interface UpdateIdResponse {
    userEmail: string;
    userNewId: string;
}

export interface GetUserEmailResponse {
    userEmail: string;
}

export interface GetUserIdResponse {
    userId: string;
}

export interface FindUserInfoResponse {
    userEmail: string;
    userId: string;
    userName: string;
    userRole: string; // Assuming Role is a string or enum
    userTierPoint: number;
    userPoint: number;
    userProvider: Provider;
}

export interface ChangeActivityResponse {
    userEmail: string;
    userId: string;
    userName: string;
    activityId: number;
    activityName: string;
}
