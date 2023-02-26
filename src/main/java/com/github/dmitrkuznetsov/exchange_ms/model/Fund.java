package com.github.dmitrkuznetsov.exchange_ms.model;

import com.github.dmitrkuznetsov.exchange_ms.model.enums.Currency;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fund {

  private Currency currency;
  private int count;
}
