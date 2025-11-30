package com.but.rebloom.domain.hobby.domain;

import lombok.*;

@Getter
@RequiredArgsConstructor
@Builder
public class HobbyScore {
    private final Hobby hobby;
    private final double distance;
}
