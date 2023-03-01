package com.github.dmitrkuznetsov.exchange_ms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

  @Email
  private String email;

  @NotBlank
  private String password;
}
