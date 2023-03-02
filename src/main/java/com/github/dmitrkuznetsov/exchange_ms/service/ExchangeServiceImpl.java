package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.ConvertRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.Money;
import com.github.dmitrkuznetsov.exchange_ms.dto.SetExchangeRateRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import com.github.dmitrkuznetsov.exchange_ms.exception.UnexpectedExchangeRate;
import com.github.dmitrkuznetsov.exchange_ms.repository.ExchangeRateRepository;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.ExchangeRate;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.ExchangeRateId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

  private final ExchangeRateRepository exchangeRateRepository;

  @Override
  public Map<Currency, Double> getRate(Currency currencyFrom) {

    List<ExchangeRate> exchangeRates = exchangeRateRepository
        .findAllByIdCurrencyFrom(currencyFrom);

    Map<Currency, Double> ratesMap = new HashMap<>();

    exchangeRates.forEach(exchangeRate -> ratesMap.put(
        exchangeRate.getId().getCurrencyTo(),
        exchangeRate.getRate()
    ));

    return ratesMap;
  }

  @Override
  @Transactional
  public Map<Currency, Double> setRate(SetExchangeRateRequest request) {

    Currency currencyFrom = request.getBaseCurrency();
    Map<Currency, Double> ratesMap = request.getRatesMap();

    ratesMap.keySet().forEach(currencyKey -> {
      ExchangeRate exchangeRate = exchangeRateRepository
          .findById(new ExchangeRateId(currencyFrom, currencyKey))
          .orElseThrow(() -> new UnexpectedExchangeRate(currencyFrom, currencyKey));

      exchangeRate.setRate(ratesMap.get(currencyKey));


      ExchangeRate reverseExchangeRate = exchangeRateRepository
          .findById(new ExchangeRateId(currencyKey, currencyFrom))
          .orElseThrow(() -> new UnexpectedExchangeRate(currencyKey, currencyFrom));

      reverseExchangeRate.setRate(1/ratesMap.get(currencyKey));
    });

    return getRate(currencyFrom);
  }

  @Override
  public Money convert(ConvertRequest request) {

    Currency currencyFrom = request.getMoneyFrom().getCurrency();
    Currency currencyTo = request.getCurrencyTo();
    double count = request.getMoneyFrom().getCount();

    double rate = exchangeRateRepository
        .findById(new ExchangeRateId(currencyFrom, currencyTo))
        .orElseThrow(() -> new UnexpectedExchangeRate(currencyFrom, currencyTo))
        .getRate();

    return new Money(currencyTo, count*rate);
  }
}
