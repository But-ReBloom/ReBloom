package com.but.rebloom.domain.channel.controller;

import com.but.rebloom.domain.channel.domain.UserChannel;
import com.but.rebloom.domain.channel.dto.request.ApplyMemberRequest;
import com.but.rebloom.domain.channel.dto.request.RejectMemberRequest;
import com.but.rebloom.domain.channel.dto.response.ApplyMemberResponse;
import com.but.rebloom.domain.channel.dto.response.RejectMemberResponse;
import com.but.rebloom.domain.channel.usecase.VerifyUserUseCase;
import com.but.rebloom.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/channel/member")
public class UserChannelController {
    private final VerifyUserUseCase verifyUserUseCase;

    // 가입 요청
    @PostMapping("/member/apply")
    public ResponseEntity<ApiResponse<ApplyMemberResponse>> applyChannel(
            @RequestBody ApplyMemberRequest request
    ) {
        UserChannel userChannel = verifyUserUseCase.applyMemberVerification(request);
        return ResponseEntity.ok(ApiResponse.success(ApplyMemberResponse.from(userChannel)));
    }

    // 가입 거절
    @PostMapping("/member/reject")
    public ResponseEntity<ApiResponse<RejectMemberResponse>> rejectChannel(
            @RequestBody RejectMemberRequest request
    ) {
        UserChannel userChannel = verifyUserUseCase.rejectMemberVerification(request);
        return ResponseEntity.ok(ApiResponse.success(RejectMemberResponse.from(userChannel)));
    }

    // 가입 승인
    @PostMapping("/member/reject")
    public ResponseEntity<ApiResponse<RejectMemberResponse>> approveChannel(
            @RequestBody RejectMemberRequest request
    ) {
        UserChannel userChannel = verifyUserUseCase.rejectMemberVerification(request);
        return ResponseEntity.ok(ApiResponse.success(RejectMemberResponse.from(userChannel)));
    }
}
