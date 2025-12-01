package com.but.rebloom.domain.hobby.exception;

import com.but.rebloom.global.exception.NotFoundException;

public class ActivityNotFoundException extends NotFoundException {
  public ActivityNotFoundException(String message) {
      super(message);
  }
}
