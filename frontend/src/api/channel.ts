import { client } from "./client";
import type { 
    CreateChannelRequest,
    CreateChannelResponse,
    SearchChannelRequest,
    FindChannelResponse,
    FindChannelDetailedInfoResponse,
    GetUserChannelInfoResponse,
    ApproveChannelResponse,
    ApplyMemberRequest,
    ApplyMemberResponse,
    RejectMemberRequest,
    RejectMemberResponse,
    ApproveMemberRequest,
    ApproveMemberResponse,
    GetUserChannelDetailedInfoResponse
} from "../types/channel";
import type { ApiResponse } from "../types/common";

export const channelApi = {
    createChannel: (data: CreateChannelRequest) => 
        client("/channel/create", {
            method: "POST",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<CreateChannelResponse>>,

    searchChannel: (data: SearchChannelRequest) => 
        client(`/channel/find/keyword?keyword=${data.keyword}`, {
            method: "GET",
        }) as Promise<ApiResponse<FindChannelResponse>>,

    getChannel: (channelId: number) => 
        client(`/channel/find/${channelId}`, {
            method: "GET",
        }) as Promise<ApiResponse<FindChannelDetailedInfoResponse>>,

    getChannelUser: (channelId: number) => 
        client(`/channel/find/${channelId}/find/member`, {
            method: "GET",
        }) as Promise<ApiResponse<GetUserChannelInfoResponse>>,

    // 승인된 채널 목록 조회
    getAllChannels: () =>
        client("/channel/find/all", {
            method: "GET",
        }) as Promise<ApiResponse<FindChannelResponse>>,

    // 승인된 채널 목록 조회 (getAllChannels와 동일)
    getApprovedChannels: () => 
        client("/channel/find/all", {
            method: "GET",
        }) as Promise<ApiResponse<FindChannelResponse>>,

    getPendingChannels: () => 
        client("/channel/admin/find/pending", {
            method: "GET",
        }) as Promise<ApiResponse<FindChannelResponse>>,

    approveChannel: (channelId: number) => 
        client(`/channel/admin/approve/${channelId}`, {
            method: "PATCH",
        }) as Promise<ApiResponse<ApproveChannelResponse>>,

    rejectChannel: (channelId: number) => 
        client(`/channel/admin/reject/${channelId}`, {
            method: "DELETE",
        }) as Promise<ApiResponse<void>>,

    findUserChannelByChannelId: (channelId: number) => 
        client(`/channel/member/find/${channelId}`, {
            method: "POST",
        }) as Promise<ApiResponse<GetUserChannelInfoResponse>>,

    findUserChannelByUserEmail: (userEmail: string) => 
        client(`/channel/member/find/email?userEmail=${userEmail}`, {
            method: "POST",
        }) as Promise<ApiResponse<GetUserChannelInfoResponse>>,

    findUserChannelByIdAndEmail: (channelId: number, userEmail: string) => 
        client(`/channel/member/find/${channelId}/email?userEmail=${userEmail}`, {
            method: "POST",
        }) as Promise<ApiResponse<GetUserChannelDetailedInfoResponse>>,

    findUserChannelByEmailAndId: (userEmail: string, channelId: number) => 
        client(`/channel/member/find/email/${channelId}?userEmail=${userEmail}`, {
            method: "POST",
        }) as Promise<ApiResponse<GetUserChannelDetailedInfoResponse>>,

    applyChannel: (data: ApplyMemberRequest) => 
        client("/channel/member/apply", {
            method: "POST",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<ApplyMemberResponse>>,

    rejectMember: (data: RejectMemberRequest) => 
        client("/channel/member/reject", {
            method: "PATCH",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<RejectMemberResponse>>,

    approveMember: (data: ApproveMemberRequest) => 
        client("/channel/member/approve", {
            method: "PATCH",
            body: JSON.stringify(data),
        }) as Promise<ApiResponse<ApproveMemberResponse>>,
};
