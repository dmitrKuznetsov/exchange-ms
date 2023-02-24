package com.github.dmitrkuznetsov.exchange_ms.model;

import com.github.dmitrkuznetsov.exchange_ms.model.enums.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Fund {

  private Currency currency;
  private int count;

  public Fund(Currency currency, int count) {
    this.currency = currency;
    this.count = count;
  }
}
