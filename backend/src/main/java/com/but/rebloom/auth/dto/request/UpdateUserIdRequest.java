package com.but.rebloom.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateUserIdRequest {
    @NotBlank
    private String updateUserId;
}
