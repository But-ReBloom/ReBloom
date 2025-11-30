package com.but.rebloom.domain.reaction.dto.response;

import com.but.rebloom.domain.reaction.domain.Heart;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateHeartResponse {
    @NotNull
    private Long heartId;
    @NotNull
    private String userId;
    @NotNull
    private String userName;
    @NotNull
    private Long postId;

    public static CreateHeartResponse from(Heart heart) {
        return CreateHeartResponse.builder()
                .heartId(heart.getHeartId())
                .userId(heart.getUser().getUserId())
                .userName(heart.getUser().getUserName())
                .postId(heart.getPost().getPostId())
                .build();
    }
}
