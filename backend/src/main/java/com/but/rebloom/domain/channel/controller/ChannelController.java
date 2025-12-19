package com.but.rebloom.domain.channel.controller;

import com.but.rebloom.domain.channel.domain.Channel;
import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.dto.request.CreateChannelRequest;
import com.but.rebloom.domain.channel.dto.request.SearchChannelRequest;
import com.but.rebloom.domain.channel.dto.response.*;
import com.but.rebloom.domain.channel.usecase.ChannelUseCase;
import com.but.rebloom.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/channel")
public class ChannelController {
    private final ChannelUseCase channelUseCase;

    // 채널 생성 요청
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateChannelResponse>> createChannel(@RequestBody CreateChannelRequest request) {
        Channel response = channelUseCase.requestCreation(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(CreateChannelResponse.from(response)));
    }

    // 채널 검색
    @GetMapping("/find/keyword")
    public ResponseEntity<ApiResponse<FindChannelResponse>> searchChannel(@ModelAttribute SearchChannelRequest request) {
        List<Channel> responses = channelUseCase.findChannel(request);
        return ResponseEntity.ok(ApiResponse.success(FindChannelResponse.from(responses)));
    }

    // 특정 채널 조회
    @GetMapping("/find/{channelId}")
    public ResponseEntity<ApiResponse<FindChannelDetailedInfoResponse>> getChannel(@PathVariable Long channelId) {
        Channel response = channelUseCase.getChannel(channelId);
        return ResponseEntity.ok(ApiResponse.success(FindChannelDetailedInfoResponse.from(response)));
    }

    // 특정 채널의 유저 목록 조회
    @GetMapping("/find/{channelId}/find/member")
    public ResponseEntity<ApiResponse<GetUserChannelInfoResponse>> getChannelUser(@PathVariable Long channelId) {
        List<UserChannel> userChannels = channelUseCase.getChannelUser(channelId);
        return ResponseEntity.ok(ApiResponse.success(GetUserChannelInfoResponse.from(userChannels)));
    }

    // 승인된 채널 목록 조회
    @GetMapping("/find/all")
    public ResponseEntity<ApiResponse<FindChannelResponse>> getApprovedChannels() {
        List<Channel> responses = channelUseCase.getApprovedChannels();
        return ResponseEntity.ok(ApiResponse.success(FindChannelResponse.from(responses)));
    }

    // 승인 대기 채널 목록 조회
    @GetMapping("/admin/find/pending")
    public ResponseEntity<ApiResponse<FindChannelResponse>> getPendingChannels() {
        List<Channel> responses = channelUseCase.getPendingChannels();
        return ResponseEntity.ok(ApiResponse.success(FindChannelResponse.from(responses)));
    }

    // 채널 승인
    @PatchMapping("/admin/approve/{channelId}")
    public ResponseEntity<ApiResponse<ApproveChannelResponse>> approveChannel(@PathVariable Long channelId) {
        Channel response = channelUseCase.approveChannel(channelId);
        return ResponseEntity.ok(ApiResponse.success(ApproveChannelResponse.from(response)));
    }

    // 채널 거절
    @DeleteMapping("/admin/reject/{channelId}")
    public ResponseEntity<ApiResponse<Void>> rejectChannel(@PathVariable Long channelId) {
        channelUseCase.rejectChannel(channelId);
        return ResponseEntity.ok(ApiResponse.success());
    }
}