package com.but.rebloom.achievement.exception;

import com.but.rebloom.common.exception.NotFoundException;

public class UserAchievementNotFoundException extends NotFoundException {
    public UserAchievementNotFoundException(String message) {
        super(message);
    }
}
