package com.but.rebloom.domain.auth.dto.request;

import com.but.rebloom.domain.auth.domain.VerificationPurpose;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SendVerificationEmailRequest {
    @NotNull
    private String userEmail;
    @NotNull
    private VerificationPurpose purpose;
}
