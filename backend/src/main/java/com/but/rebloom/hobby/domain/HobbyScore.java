package com.but.rebloom.hobby.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HobbyScore {
    private final HobbyWeight hobbyWeight;
    private final double distance;
}
