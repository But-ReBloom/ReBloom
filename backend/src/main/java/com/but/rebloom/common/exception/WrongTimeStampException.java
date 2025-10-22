package com.but.rebloom.common.exception;

public class WrongTimeStampException extends AuthenticationException {
  public WrongTimeStampException(String message) {
    super(message);
  }
}
