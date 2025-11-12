package com.but.rebloom.auth.dto.request;

import com.but.rebloom.auth.domain.Provider;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotNull
    private String userEmail;
    @NotNull
    private String userPassword;
    @NotNull
    private Provider userProvider;
}
