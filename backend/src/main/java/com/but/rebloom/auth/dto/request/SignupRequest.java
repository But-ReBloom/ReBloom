package com.but.rebloom.auth.dto.request;

import com.but.rebloom.auth.domain.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    @NonNull
    private String userEmail;

    @NonNull
    private String userId;

    @NonNull
    private String userPassword;

    @NonNull
    private String userName;
}
