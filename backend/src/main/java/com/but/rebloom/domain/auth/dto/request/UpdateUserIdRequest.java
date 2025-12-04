package com.but.rebloom.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserIdRequest {
    @NotBlank
    private String updateUserId;
}
