package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;

import java.time.LocalDate;
import java.util.Map;


public interface AdminService {
  Map<Currency, Double> getTotalMoney(Currency currency);
  int getOperationCount(LocalDate dateFrom, LocalDate dateTo);
}
