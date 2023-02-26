package com.github.dmitrkuznetsov.exchange_ms.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawRequest {

  private Fund fund;
  private String wallet;
}
