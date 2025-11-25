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

export interface FindEmailRequest {
    // Add fields based on backend if needed
}

export interface FindIdRequest {
    // Add fields based on backend if needed
}
