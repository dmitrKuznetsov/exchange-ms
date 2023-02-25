package com.github.dmitrkuznetsov.exchange_ms.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
  UNKNOWN,
  USER,
  ADMIN;

  @Override
  public String getAuthority() {
    return name();
  }
}
