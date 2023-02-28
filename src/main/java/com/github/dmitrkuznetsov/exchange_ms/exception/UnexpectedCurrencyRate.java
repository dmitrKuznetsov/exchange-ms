package com.github.dmitrkuznetsov.exchange_ms.exception;

import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;

public class UnexpectedCurrencyRate extends RuntimeException{

  public UnexpectedCurrencyRate(Currency currencyFrom, Currency currencyTo) {
    super("Unexpected currency rate: from " + currencyFrom.name() + " to " + currencyTo.name());
  }
}
