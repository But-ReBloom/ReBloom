package com.but.rebloom.auth.dto.request;

import com.but.rebloom.auth.domain.VerificationPurpose;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class SendVerificationEmailRequest {
    @NonNull
    private String userEmail;
    @NonNull
    private VerificationPurpose purpose;
}
