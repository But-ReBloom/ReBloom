package com.but.rebloom.channel.dto.response;

import com.but.rebloom.channel.domain.Heart;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateHeartResponse {
    private Long heartId;
    private String userId;
    private String userName;
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
