package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.Money;
import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import com.github.dmitrkuznetsov.exchange_ms.repository.OperationRepository;
import com.github.dmitrkuznetsov.exchange_ms.repository.WalletRepository;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.Operation;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

  private final WalletRepository walletRepository;
  private final OperationRepository operationRepository;

  @Override
  public Money getTotalMoney(Currency currency) {

    double totalMoney = walletRepository
        .findByCurrency(currency)
        .stream().mapToDouble(Wallet::getCount)
        .sum();

    return new Money(currency, totalMoney);
  }

  @Override
  public int getOperationCount(LocalDate dateFrom, LocalDate dateTo) {

    List<Operation> operations = operationRepository
        .findByDateBetween(dateFrom, dateTo);

    return operations.size();
  }
}
