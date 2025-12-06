package com.but.rebloom.domain.auth.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindEmailRequest {
    @NotNull
    private String userId;
    @NotNull
    private String userPassword;
}
