package com.github.dmitrkuznetsov.exchange_ms.dto;

import lombok.Data;

@Data
public class ExceptionResponse {

  private final String message;

  public ExceptionResponse(String message) {
    this.message = message;
  }

  public ExceptionResponse(RuntimeException ex) {
    this.message = ex.getMessage();
  }
}
