package com.github.dmitrkuznetsov.exchange_ms.model;

import com.github.dmitrkuznetsov.exchange_ms.model.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {

  private String email;
  private String password;
  private Role role;
}
