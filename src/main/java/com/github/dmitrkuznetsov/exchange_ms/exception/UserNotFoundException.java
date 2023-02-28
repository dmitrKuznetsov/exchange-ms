package com.github.dmitrkuznetsov.exchange_ms.exception;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException() {
    super("User not found");
  }
}
