package com.github.dmitrkuznetsov.exchange_ms.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawCryptoRequest {

  private Money money;
  private String wallet;
}
