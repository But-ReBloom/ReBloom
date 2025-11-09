package com.but.rebloom.achievement.domain;

import java.io.Serializable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserAchievementId implements Serializable {
    private String userEmail;
    private Long achieveId;
}
