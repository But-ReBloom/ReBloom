package com.but.rebloom.channel.dto.response;

import com.but.rebloom.channel.domain.Comment;
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
public class FindCommentResponse {
    private List<CreateCommentResponse> comments;
    private int totalCount;

    public static FindCommentResponse from(List<Comment> comments) {
        List<CreateCommentResponse> commentResponses = comments.stream()
                .map(CreateCommentResponse::from)
                .collect(Collectors.toList());

        return FindCommentResponse.builder()
                .comments(commentResponses)
                .totalCount(commentResponses.size())
                .build();
    }
}
