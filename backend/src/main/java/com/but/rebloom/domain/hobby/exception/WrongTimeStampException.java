package com.but.rebloom.domain.hobby.exception;

import com.but.rebloom.global.exception.EntityException;

public class WrongTimeStampException extends EntityException {
  public WrongTimeStampException(String message) {
    super(message);
  }
}
