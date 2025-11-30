package com.but.rebloom.domain.achievement.exception;

import com.but.rebloom.global.exception.NotFoundException;

public class UserAchievementNotFoundException extends NotFoundException {
    public UserAchievementNotFoundException(String message) {
        super(message);
    }
}
