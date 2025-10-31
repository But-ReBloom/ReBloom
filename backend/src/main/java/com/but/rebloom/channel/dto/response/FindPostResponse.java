package com.but.rebloom.channel.dto.response;

import com.but.rebloom.channel.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindPostResponse {
    private List<CreatePostResponse> posts;
    private int totalCount;

    public static FindPostResponse from(List<Post> posts) {
        List<CreatePostResponse> postResponses = posts.stream()
                .map(CreatePostResponse::from)
                .collect(Collectors.toList());

        return FindPostResponse.builder()
                .posts(postResponses)
                .totalCount(postResponses.size())
                .build();
    }
}
