package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.Money;
import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;


public interface AdminService {
  Money getTotalMoney(Currency currency);
  int getOperationCount(Currency currency);
}
