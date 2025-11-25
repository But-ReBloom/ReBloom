import { client } from "./client";
import type { 
    LoginRequest, 
    LoginResponse, 
    SignupRequest, 
    SignupResponse, 
    SendVerificationEmailRequest,
    VerifyCodeRequest
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
        }),

    verifyCode: (data: VerifyCodeRequest) =>
        client("/auth/email/verify", {
            method: "POST",
            body: JSON.stringify(data),
        }),
};
