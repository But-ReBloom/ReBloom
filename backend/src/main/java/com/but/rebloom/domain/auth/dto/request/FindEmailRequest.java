package com.but.rebloom.domain.auth.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FindEmailRequest {
    @NotNull
    private String userId;
    @NotNull
    private String userPassword;
}
