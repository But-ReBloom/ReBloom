package com.but.rebloom.reaction.dto.response;

import com.but.rebloom.reaction.domain.Heart;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
