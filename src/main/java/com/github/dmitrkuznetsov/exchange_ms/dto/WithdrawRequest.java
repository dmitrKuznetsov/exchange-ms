package com.github.dmitrkuznetsov.exchange_ms.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawRequest {

  private Money money;
  private String creditCard;
}
