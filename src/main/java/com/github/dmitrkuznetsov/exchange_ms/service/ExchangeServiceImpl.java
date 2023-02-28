package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.ConvertRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.Money;
import com.github.dmitrkuznetsov.exchange_ms.dto.SetExchangeRateRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import com.github.dmitrkuznetsov.exchange_ms.exception.UnexpectedCurrencyRate;
import com.github.dmitrkuznetsov.exchange_ms.repository.ExchangeRateRepository;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.ExchangeRate;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.ExchangeRateId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

  private final ExchangeRateRepository exchangeRateRepository;

  @Override
  public List<Money> getRate(Currency currency) {
    List<ExchangeRate> exchangeRates = exchangeRateRepository.findAllByIdCurrencyFrom(currency);

    return exchangeRates.stream().map(
        rate -> new Money(rate.getId().getCurrencyTo(), rate.getRate())
    ).toList();
  }

  @Override
  public Money convert(ConvertRequest request) {

    Currency currencyFrom = request.getMoneyFrom().getCurrency();
    Currency currencyTo = request.getCurrencyTo();
    double count = request.getMoneyFrom().getCount();

    double rate = exchangeRateRepository
        .findById(new ExchangeRateId(currencyFrom, currencyTo))
        .orElseThrow(() -> new UnexpectedCurrencyRate(currencyFrom, currencyTo))
        .getRate();

    return new Money(currencyTo, count*rate);
  }

  @Override
  public List<Money> setRate(SetExchangeRateRequest request) {
    return null;
  }
}
