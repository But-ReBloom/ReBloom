package com.but.rebloom.domain.hobby.domain;

import lombok.*;

@Getter
@RequiredArgsConstructor
@Builder
public class HobbyScore {
    private final HobbyWeight hobbyWeight;
    private final double distance;
}
