import { client } from "./client";
import type { 
    LoginRequest, 
    LoginResponse, 
    SignupRequest, 
    SignupResponse, 
    SendVerificationEmailRequest,
    VerifyCodeRequest,
    GoogleLoginAuthorizeCodeRequest,
    GoogleUserInfoResponse,
    UpdateUserIdRequest,
    UpdateIdResponse,
    UpdateUserPasswordRequest,
    GetUserEmailResponse,
    FindEmailRequest,
    FindIdRequest,
    GetUserIdResponse,
    FindUserInfoResponse,
    ChangeActivityRequest,
    ChangeActivityResponse
} from "../types/auth";
import type { ApiResponse } from "../types/common";

export const authApi = {
    login: (data: LoginRequest) => 
        client("/auth/login", {
            method: "POST",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<LoginResponse>>,

    signup: (data: SignupRequest) => 
        client("/auth/signup", {
            method: "POST",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<SignupResponse>>,

    sendVerificationEmail: (data: SendVerificationEmailRequest) =>
        client("/auth/email/send", {
            method: "POST",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<GetUserEmailResponse>>,

    verifyCode: (data: VerifyCodeRequest) =>
        client("/auth/email/verify", {
            method: "POST",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<GetUserEmailResponse>>,

    googleLogin: (data: GoogleLoginAuthorizeCodeRequest) =>
        client("/auth/login/google", {
            method: "POST",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<GoogleUserInfoResponse>>,

    updateUserId: (data: UpdateUserIdRequest) =>
        client("/auth/update/id", {
            method: "PATCH",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<UpdateIdResponse>>,

    updateUserPassword: (data: UpdateUserPasswordRequest) =>
        client("/auth/update/pw", {
            method: "PATCH",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<GetUserEmailResponse>>,

    findUserEmail: (data: FindEmailRequest) =>
        client("/auth/find/email", {
            method: "POST",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<GetUserEmailResponse>>,

    findUserId: (data: FindIdRequest) =>
        client("/auth/find/id", {
            method: "POST",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<GetUserIdResponse>>,

    findCurrentUser: () =>
        client("/auth/find/current-user", {
            method: "POST",
        }) as Promise<ApiResponse<FindUserInfoResponse>>,

    changeActivity: (data: ChangeActivityRequest) =>
        client("/auth/update/activity", {
            method: "PATCH",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<ChangeActivityResponse>>,
};
