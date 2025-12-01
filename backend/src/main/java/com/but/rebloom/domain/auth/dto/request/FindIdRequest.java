package com.but.rebloom.domain.auth.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FindIdRequest {
    @NotNull
    private String userEmail;
    @NotNull
    private String userPassword;
}
