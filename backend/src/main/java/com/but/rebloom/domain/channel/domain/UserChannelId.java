package com.but.rebloom.domain.channel.domain;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserChannelId implements Serializable {
    // 다중키 속성 정의
    private String user;
    private Long channel;
}


