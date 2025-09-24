package com.but.rebloom.auth.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    @NotNull
    private String userEmail;
    @NotNull
    private String userId;
    @NotNull
    private String userPassword;
    @NotNull
    private String userName;
}
