package com.but.rebloom.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class GoogleLoginAuthorizeCodeRequest {
    @NotBlank
    private String authorizationCode;
}
