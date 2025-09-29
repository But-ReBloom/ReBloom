package com.but.rebloom.channel.dto.request;

import com.but.rebloom.channel.domain.Status;
import com.but.rebloom.channel.domain.Type;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagePostRequest {
    @NotNull
    private Long postId;

    private Type postType;
    private Status postStatus;

}
