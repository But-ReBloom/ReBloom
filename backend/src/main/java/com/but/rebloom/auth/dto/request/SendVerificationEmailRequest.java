package com.but.rebloom.auth.dto.request;

import com.but.rebloom.auth.domain.VerificationPurpose;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendVerificationEmailRequest {
    @NotNull
    private String userEmail;
    @NotNull
    private VerificationPurpose purpose;
}
