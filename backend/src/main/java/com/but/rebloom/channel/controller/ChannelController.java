package com.but.rebloom.channel.controller;

import com.but.rebloom.channel.domain.Channel;
import com.but.rebloom.channel.dto.request.CreateChannelRequest;
import com.but.rebloom.channel.dto.response.CreateChannelResponse;
import com.but.rebloom.channel.dto.response.FindChannelResponse;
import com.but.rebloom.channel.usecase.ChannelUseCase;
import com.but.rebloom.common.dto.ApiResponse;
import jakarta.validation.Valid;
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
    @PostMapping("/request")
    public ResponseEntity<ApiResponse<CreateChannelResponse>> createChannel(@Valid @RequestBody CreateChannelRequest request) {
        Channel channel = channelUseCase.requestCreation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(CreateChannelResponse.from(channel)));
    }

    // 채널 검색
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<FindChannelResponse>> searchChannel(String keyword) {
        List<Channel> channels = channelUseCase.findChannel(keyword);
        return ResponseEntity.ok(ApiResponse.success(FindChannelResponse.from(channels)));
    }

    // 특정 채널 조회
    @GetMapping("/{channelId}")
    public ResponseEntity<ApiResponse<CreateChannelResponse>> getChannel(@PathVariable Long channelId) {
        Channel channel = channelUseCase.getChannel(channelId);
        return ResponseEntity.ok(ApiResponse.success(CreateChannelResponse.from(channel)));
    }

    // 승인된 채널 목록 조회
    @GetMapping("/admin/approve")
    public ResponseEntity<ApiResponse<FindChannelResponse>> getApprovedChannels() {
        List<Channel> channels = channelUseCase.getApprovedChannels();
        return ResponseEntity.ok(ApiResponse.success(FindChannelResponse.from(channels)));
    }

    // 승인 대기 채널 목록 조회
    @GetMapping("/admin/pending")
    public ResponseEntity<ApiResponse<FindChannelResponse>> getPendingChannels() {
        List<Channel> channels = channelUseCase.getPendingChannels();
        return ResponseEntity.ok(ApiResponse.success(FindChannelResponse.from(channels)));
    }

    // 채널 승인
    @PatchMapping("/admin/{channelId}/approve")
    public ResponseEntity<ApiResponse<CreateChannelResponse>> approveChannel(@PathVariable Long channelId) {
        Channel channel = channelUseCase.approveChannel(channelId);
        return ResponseEntity.ok(ApiResponse.success(CreateChannelResponse.from(channel)));
    }

    // 채널 거절
    @DeleteMapping("/admin/{channelId}/reject")
    public ResponseEntity<ApiResponse<Void>> rejectChannel(@PathVariable Long channelId) {
        channelUseCase.rejectChannel(channelId);
        return ResponseEntity.ok(ApiResponse.success());
    }
}
