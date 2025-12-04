package com.but.rebloom.domain.reaction.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CheckHeartExistsRequest {
    @NotBlank
    private String userId;
    @NotNull
    private Long postId;
}
