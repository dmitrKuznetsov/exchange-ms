package com.github.dmitrkuznetsov.exchange_ms.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

  private String token;
}
