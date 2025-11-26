package com.but.rebloom.domain.achievement.exception;

import com.but.rebloom.global.exception.NotFoundException;

public class AchievementNotFoundException extends NotFoundException {
    public AchievementNotFoundException(String message) {
        super(message);
    }
}
