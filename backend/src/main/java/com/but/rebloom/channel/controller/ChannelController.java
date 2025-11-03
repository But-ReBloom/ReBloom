package com.but.rebloom.channel.controller;

import com.but.rebloom.channel.domain.Channel;
import com.but.rebloom.channel.dto.request.CreateChannelRequest;
import com.but.rebloom.channel.dto.response.CreateChannelResponse;
import com.but.rebloom.channel.dto.response.FindChannelResponse;
import com.but.rebloom.channel.usecase.ChannelUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/channels")
public class ChannelController {
    private final ChannelUseCase channelUseCase;

    // 채널 생성 요청
    @PostMapping("/request")
    public ResponseEntity<CreateChannelResponse> createChannel(@Valid @RequestBody CreateChannelRequest request) {
        Channel channel = channelUseCase.requestCreation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateChannelResponse.from(channel));
    }

    // 채널 검색
    @GetMapping("/search")
    public ResponseEntity<FindChannelResponse> searchChannel(String keyword) {
        List<Channel> channels = channelUseCase.findChannel(keyword);
        return ResponseEntity.ok(FindChannelResponse.from(channels));
    }

    // 특정 채널 조회
    @GetMapping("/{channelId}")
    public ResponseEntity<CreateChannelResponse> getChannel(@PathVariable Long channelId) {
        Channel channel = channelUseCase.getChannel(channelId);
        return ResponseEntity.ok(CreateChannelResponse.from(channel));
    }

    // 승인된 채널 목록 조회
    @GetMapping("/admin/approve")
    public ResponseEntity<FindChannelResponse> getApprovedChannels() {
        List<Channel> channels = channelUseCase.getApprovedChannels();
        return ResponseEntity.ok(FindChannelResponse.from(channels));
    }

    // 승인 대기 채널 목록 조회
    @GetMapping("/admin/pending")
    public ResponseEntity<FindChannelResponse> getPendingChannels() {
        List<Channel> channels = channelUseCase.getPendingChannels();
        return ResponseEntity.ok(FindChannelResponse.from(channels));
    }

    // 채널 승인
    @PatchMapping("/admin/{channelId}/approve")
    public ResponseEntity<CreateChannelResponse> approveChannel(@PathVariable Long channelId) {
        Channel channel = channelUseCase.approveChannel(channelId);
        return ResponseEntity.ok(CreateChannelResponse.from(channel));
    }

    // 채널 거절
    @DeleteMapping("/admin/{channelId}/reject")
    public ResponseEntity<Void> rejectChannel(@PathVariable Long channelId) {
        channelUseCase.rejectChannel(channelId);
        return ResponseEntity.noContent().build();
    }
}
