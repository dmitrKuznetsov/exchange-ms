package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.ConvertRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.Fund;
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
  public List<Fund> getRate(Currency currency) {
    List<ExchangeRate> exchangeRates = exchangeRateRepository.findAllByIdCurrencyFrom(currency);

    return exchangeRates.stream().map(
        rate -> new Fund(rate.getId().getCurrencyTo(), rate.getRate())
    ).toList();
  }

  @Override
  public Fund convert(ConvertRequest request) {

    Currency currencyFrom = request.getFundFrom().getCurrency();
    Currency currencyTo = request.getCurrencyTo();
    double count = request.getFundFrom().getCount();

    double rate = exchangeRateRepository
        .findById(new ExchangeRateId(currencyFrom, currencyTo))
        .orElseThrow(() -> new UnexpectedCurrencyRate(currencyFrom, currencyTo))
        .getRate();

    return new Fund(currencyTo, count*rate);
  }
}
