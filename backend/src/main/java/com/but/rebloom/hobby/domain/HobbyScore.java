package com.but.rebloom.hobby.domain;

import lombok.*;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class HobbyScore {
    private final HobbyWeight hobbyWeight;
    private final double distance;
}
