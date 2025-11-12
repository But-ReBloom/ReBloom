import axios from 'axios';
import type {
    FindEmailRequest, FindEmailResponse, FindUserInfoResponse, GoogleUserInfoResponse, LoginRequest, LoginResponse,
    SendVerificationEmailRequest, SendVerificationEmailResponse, SignupRequest, SignupResponse, UpdateIdRequest,
    UpdateIdResponse, UpdatePwRequest, UpdatePwResponse, VerifyCodeRequest, VerifyCodeResponse
} from '../types/api';

const api = axios.create({
  baseURL: '/', // 프록시 설정을 사용하므로 상대 경로로 변경
  headers: {
    'Content-Type': 'application/json',
  },
});

// JWT 토큰을 헤더에 추가하는 인터셉터
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// Auth APIs
export const sendVerificationEmail = (data: SendVerificationEmailRequest) => {
    return api.post<SendVerificationEmailResponse>('/auth/email/send', data);
};

export const verifyCode = (data: VerifyCodeRequest) => {
    return api.post<VerifyCodeResponse>('/auth/email/verify', data);
};

export const signup = (data: SignupRequest) => {
    return api.post<SignupResponse>('/auth/signup', data);
};

export const login = (data: LoginRequest) => {
    return api.post<LoginResponse>('/auth/login', data);
};

export const googleLogin = (authorizationCode: string) => {
    return api.post<GoogleUserInfoResponse>('/auth/login/google', authorizationCode);
};

export const updateUserId = (data: UpdateIdRequest) => {
    return api.patch<UpdateIdResponse>('/auth/update/id', data);
};

export const updateUserPw = (data: UpdatePwRequest) => {
    return api.patch<UpdatePwResponse>('/auth/update/pw', data);
};

export const findUserEmail = (data: FindEmailRequest) => {
    return api.post<FindEmailResponse>('/auth/find/email', data);
};

export const findCurrentUser = () => {
    return api.post<FindUserInfoResponse>('/auth/current-user');
};

export default api;
