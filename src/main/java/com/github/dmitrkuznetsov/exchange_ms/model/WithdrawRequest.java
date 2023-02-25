package com.github.dmitrkuznetsov.exchange_ms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WithdrawRequest {

  private Fund fund;
  private String wallet;
}
