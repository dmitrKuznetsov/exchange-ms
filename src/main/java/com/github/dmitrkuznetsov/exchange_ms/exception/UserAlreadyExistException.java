package com.github.dmitrkuznetsov.exchange_ms.exception;

public class UserAlreadyExistException extends RuntimeException {

  public UserAlreadyExistException(String email) {
    super("User with " + email + " is already exist");
  }
}
