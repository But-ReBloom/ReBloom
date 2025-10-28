package com.but.rebloom.hobby.exception;

import com.but.rebloom.common.exception.EntityException;

public class WrongTimeStampException extends EntityException {
  public WrongTimeStampException(String message) {
    super(message);
  }
}
