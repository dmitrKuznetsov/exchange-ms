package com.github.dmitrkuznetsov.exchange_ms.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawCryptoRequest {
  private Fund fund;
  private String wallet;
}
