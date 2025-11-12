// Enums
export enum VerificationPurpose {
    SIGN_UP = 'SIGN_UP',
    UPDATE_INFO = 'UPDATE_INFO',
}

export enum Provider {
    SELF = 'SELF',
    GOOGLE = 'GOOGLE',
    DODAM = 'DODAM',
}

export enum Role {
    USER = 'USER',
    ADMIN = 'ADMIN',
}

// Auth Requests
export interface SendVerificationEmailRequest {
    userEmail: string;
    purpose: VerificationPurpose;
}

export interface VerifyCodeRequest {
    userEmail: string;
    code: string;
    purpose: VerificationPurpose;
}

export interface SignupRequest {
    userEmail: string;
    userId: string;
    userPassword: string;
    userName: string;
    provider: Provider;
}

export interface LoginRequest {
    userEmail: string;
    userPassword: string;
    provider: Provider;
}

export interface UpdateIdRequest {
    updateId: string;
}

export interface UpdatePwRequest {
    updatePw: string;
}

export interface FindEmailRequest {
    userId: string;
    password: string;
}

// Auth Responses
export interface SendVerificationEmailResponse {
    success: boolean;
    userEmail: string;
}

export interface VerifyCodeResponse {
    success: boolean;
    userEmail: string;
}

export interface SignupResponse {
    success: boolean;
    userId: string;
    userName: string;
    provider: Provider;
}

export interface LoginResponse {
    success: boolean;
    userEmail: string;
    provider: Provider;
    token: string;
}

export interface GoogleUserInfoResponse {
    id: string;
    email: string;
    name: string;
    accessToken: string;
    provider: Provider;
}

export interface UpdateIdResponse {
    success: boolean;
    userEmail: string;
    userNewId: string;
}

export interface UpdatePwResponse {
    success: boolean;
    userEmail: string;
}

export interface FindEmailResponse {
    success: boolean;
    userEmail: string;
}

export interface FindUserInfoResponse {
    success: boolean;
    userEmail: string;
    userId: string;
    userName: string;
    provider: Provider;
    role: Role;
    createdDate: string;
}
