package com.github.dmitrkuznetsov.exchange_ms.dto;

import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConvertRequest {

  private Fund fundFrom;
  private Currency currencyTo;
}
