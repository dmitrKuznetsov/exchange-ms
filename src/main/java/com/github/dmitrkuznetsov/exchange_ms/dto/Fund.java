package com.github.dmitrkuznetsov.exchange_ms.dto;

import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fund {

  private Currency currency;
  private int count;
}
