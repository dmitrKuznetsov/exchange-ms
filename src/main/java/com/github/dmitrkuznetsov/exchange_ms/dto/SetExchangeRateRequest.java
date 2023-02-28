package com.github.dmitrkuznetsov.exchange_ms.dto;

import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SetExchangeRateRequest {

  private Currency baseCurrency;
  private List<Money> rates;
}
