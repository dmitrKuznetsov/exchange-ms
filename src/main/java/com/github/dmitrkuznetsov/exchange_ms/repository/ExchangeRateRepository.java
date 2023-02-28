package com.github.dmitrkuznetsov.exchange_ms.repository;

import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.ExchangeRate;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.ExchangeRateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, ExchangeRateId> {
  List<ExchangeRate> findAllByIdCurrencyFrom(Currency currencyFrom);

  Optional<ExchangeRate> findById(ExchangeRateId exchangeRateId);
}
