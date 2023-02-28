package com.github.dmitrkuznetsov.exchange_ms.exception;

import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;

public class NotEnoughMoney extends RuntimeException{

  public NotEnoughMoney(Currency currency) {
    super("Not enough " + currency.name());
  }
}
