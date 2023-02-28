package com.github.dmitrkuznetsov.exchange_ms.dto;

import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Money {

  private Currency currency;
  private double count;
}
