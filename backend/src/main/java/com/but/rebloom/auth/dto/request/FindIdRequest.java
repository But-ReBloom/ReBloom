package com.but.rebloom.auth.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindIdRequest {
    @NotNull
    private String userEmail;
    @NotNull
    private String password;
}
