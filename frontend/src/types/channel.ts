export interface CreateChannelRequest {
    channelTitle: string;
    channelIntro: string;
    channelDescription: string;
    userEmail: string;
    channelLinkedHobby1: number;
    channelLinkedHobby2?: number;
    channelLinkedHobby3?: number;
}

export interface SearchChannelRequest {
    keyword: string;
}

export interface ChannelMemberRequest {
    channelId: number;
    userEmail: string;
}

export interface ApplyMemberRequest {
    channelId: number;
    applyMessage: string;
}

export type ApproveMemberRequest = ChannelMemberRequest;
export type RejectMemberRequest = ChannelMemberRequest;

export interface FindOneChannelResponse {
    channelName: string;
    userId: string;
    userTier: string;
    channelIntro: string;
    linkedHobbyId1: number;
    linkedHobbyName1: string;
    linkedHobbyId2?: number;
    linkedHobbyName2?: string;
    linkedHobbyId3?: number;
    linkedHobbyName3?: string;
}

export interface CreateChannelResponse {
    channelId: number;
    userId: string;
    userTier: string;
    channelTitle: string;
    channelIntro: string;
    channelDescription: string;
    isAccepted: boolean;
    channelCreatedAt: string;
    linkedHobbyId1: number;
    linkedHobbyName1: string;
    linkedHobbyId2?: number;
    linkedHobbyName2?: string;
    linkedHobbyId3?: number;
    linkedHobbyName3?: string;
}

export interface FindChannelResponse {
    responses: FindOneChannelResponse[];
    totalCount: number;
}

export interface FindChannelDetailedInfoResponse extends CreateChannelResponse {
}

export type VerifyStatus = "WAITING" | "APPROVED" | "REJECTED";

export interface GetUserChannelDetailedInfoResponse {
    userEmail: string;
    channelId: number;
    verifyStatus: VerifyStatus;
    applyMessage: string;
}

export interface GetUserChannelInfoResponse {
    userChannels: GetUserChannelDetailedInfoResponse[];
    applyCount: number;
}

export interface ApproveChannelResponse extends CreateChannelResponse {}

export interface ApplyMemberResponse {
    userEmail: string;
    channelId: number;
    applyMessage: string;
    verifyStatus: VerifyStatus;
}

export interface RejectMemberResponse {
    userEmail: string;
    channelId: number;
    verifyStatus: VerifyStatus;
}

export interface ApproveMemberResponse {
    userEmail: string;
    channelId: number;
    verifyStatus: VerifyStatus;
}
