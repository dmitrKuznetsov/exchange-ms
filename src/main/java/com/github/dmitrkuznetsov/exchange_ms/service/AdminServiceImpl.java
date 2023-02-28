package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.Money;
import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import com.github.dmitrkuznetsov.exchange_ms.repository.WalletRepository;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

  private final WalletRepository walletRepository;

  @Override
  public Money getTotalMoney(Currency currency) {

    double totalMoney = walletRepository
        .findByCurrency(currency)
        .stream().mapToDouble(Wallet::getCount)
        .sum();

    return new Money(currency, totalMoney);
  }

  @Override
  public int getOperationCount(Currency currency) {
    return 0;
  }
}
