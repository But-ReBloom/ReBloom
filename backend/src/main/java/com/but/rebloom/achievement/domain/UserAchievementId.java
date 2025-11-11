package com.but.rebloom.achievement.domain;

import java.io.Serializable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserAchievementId implements Serializable {
    // 다중키 속성 정의
    private String userEmail;
    private Long achievementId;
}
