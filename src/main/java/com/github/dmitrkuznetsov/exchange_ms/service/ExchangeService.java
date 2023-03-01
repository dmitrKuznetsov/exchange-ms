package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.ConvertRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.Money;
import com.github.dmitrkuznetsov.exchange_ms.dto.SetExchangeRateRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;

import java.util.Map;

public interface ExchangeService {
  Map<Currency, Double> getRate(Currency currency);

  Map<Currency, Double> setRate(SetExchangeRateRequest request);

  Money convert(ConvertRequest request);
}
