package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.ConvertRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.Money;
import com.github.dmitrkuznetsov.exchange_ms.dto.SetExchangeRateRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;

import java.util.List;

public interface ExchangeService {
  List<Money> getRate(Currency currency);

  Money convert(ConvertRequest request);

  List<Money> setRate(SetExchangeRateRequest request);
}
