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

export type ApplyMemberRequest = ChannelMemberRequest;
export type ApproveMemberRequest = ChannelMemberRequest;
export type RejectMemberRequest = ChannelMemberRequest;

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
    channels: CreateChannelResponse[];
}

export interface FindChannelDetailedInfoResponse extends CreateChannelResponse {
    // Add detailed fields if any different from CreateChannelResponse
}

export interface UserChannelResponse {
    userChannelId: number;
    userId: string;
    channelId: number;
    isAccepted: boolean;
    appliedAt: string;
}

export interface GetUserChannelInfoResponse {
    userChannels: UserChannelResponse[];
}

export interface GetUserChannelDetailedInfoResponse extends UserChannelResponse {
    // Add detailed fields if any
}

export interface ApproveChannelResponse extends CreateChannelResponse {}

export type ApplyMemberResponse = UserChannelResponse;
export type RejectMemberResponse = UserChannelResponse;
export type ApproveMemberResponse = UserChannelResponse;
