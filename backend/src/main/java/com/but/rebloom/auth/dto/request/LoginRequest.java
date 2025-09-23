package com.but.rebloom.auth.dto.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NonNull
    private String userEmail;
    @NonNull
    private String userPassword;
}
