package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.ConvertRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.Fund;
import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;

import java.util.List;

public interface ExchangeService {
  List<Fund> getRate(Currency currency);

  Fund convert(ConvertRequest request);
}
