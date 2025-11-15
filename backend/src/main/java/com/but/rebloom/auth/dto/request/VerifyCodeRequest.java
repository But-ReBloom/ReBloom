package com.but.rebloom.auth.dto.request;

import com.but.rebloom.auth.domain.VerificationPurpose;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class VerifyCodeRequest {
    @NotNull
    private String userEmail;
    @NotNull
    private String code;
    @NotNull
    private VerificationPurpose purpose;
}
