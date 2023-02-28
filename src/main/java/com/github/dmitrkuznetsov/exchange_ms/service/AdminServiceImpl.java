package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.Money;
import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
  @Override
  public Money getTotalMoney(Currency currency) {
    return null;
  }

  @Override
  public int getOperationCount(Currency currency) {
    return 0;
  }
}
