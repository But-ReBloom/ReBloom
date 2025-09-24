package com.but.rebloom.auth.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePwRequest {
    @NotNull
    private String userEmail;
    @NotNull
    private String userPassword;
}
