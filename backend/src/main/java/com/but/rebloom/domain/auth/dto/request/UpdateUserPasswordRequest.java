package com.but.rebloom.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateUserPasswordRequest {
    @NotBlank
    private String updateUserPassword;
}
