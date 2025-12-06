package com.but.rebloom.domain.auth.dto.request;

import com.but.rebloom.domain.auth.domain.VerificationPurpose;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VerifyCodeRequest {
    @NotNull
    private String userEmail;
    @NotNull
    private String code;
    @NotNull
    private VerificationPurpose purpose;
}
