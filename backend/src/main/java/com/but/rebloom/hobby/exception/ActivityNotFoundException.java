package com.but.rebloom.hobby.exception;

import com.but.rebloom.common.exception.NotFoundException;

public class ActivityNotFoundException extends NotFoundException {
  public ActivityNotFoundException(String message) {
      super(message);
  }
}
