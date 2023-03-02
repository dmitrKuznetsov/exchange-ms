package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.ConvertRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.Money;
import com.github.dmitrkuznetsov.exchange_ms.repository.ExchangeRateRepository;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.ExchangeRate;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.ExchangeRateId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency.*;

class ExchangeServiceTest {

  private ExchangeService exchangeService;
  private ExchangeRateRepository exchangeRateRepository;
  private final static double RUB_TON_RATE = 0.00001;

  @BeforeEach
  void init() {
    exchangeRateRepository = Mockito.mock(ExchangeRateRepository.class);
    exchangeService = new ExchangeServiceImpl(exchangeRateRepository);

    ExchangeRateId id = new ExchangeRateId(RUB, TON);
    ExchangeRate exchangeRate = new ExchangeRate(id, RUB_TON_RATE);
    Mockito.when(exchangeRateRepository.findById(id)).thenReturn(Optional.of(exchangeRate));
  }

  @Test
  public void shouldProperlyConvert() {
    // given
    double count = 1000;
    ConvertRequest request = new ConvertRequest(new Money(RUB, count), TON);

    // when
    Money response = exchangeService.convert(request);

    // then
    assertEquals(count*RUB_TON_RATE, response.getCount());
  }
}