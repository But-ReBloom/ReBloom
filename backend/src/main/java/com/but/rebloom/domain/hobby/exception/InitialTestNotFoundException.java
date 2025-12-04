package com.but.rebloom.domain.hobby.exception;

import com.but.rebloom.global.exception.NotFoundException;

public class InitialTestNotFoundException extends NotFoundException {
  public InitialTestNotFoundException(String message) {
      super(message);
  }
}
