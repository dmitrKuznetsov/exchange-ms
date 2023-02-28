package com.github.dmitrkuznetsov.exchange_ms.dto;

import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConvertRequest {

  private Money moneyFrom;
  private Currency currencyTo;
}
