package com.but.rebloom.achievement.exception;

import com.but.rebloom.common.exception.NotFoundException;

public class AchievementNotFoundException extends NotFoundException {
    public AchievementNotFoundException(String message) {
        super(message);
    }
}
