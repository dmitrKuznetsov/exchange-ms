package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.SetExchangeRateRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import com.github.dmitrkuznetsov.exchange_ms.repository.ExchangeRateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class ExchangeServiceIntegrationTest {

  private final ExchangeService exchangeService;

  @Autowired
  public ExchangeServiceIntegrationTest(ExchangeRateRepository exchangeRateRepository) {
    exchangeService = new ExchangeServiceImpl(exchangeRateRepository);
  }

  @Sql(scripts = {"/sql/clear_dbs.sql", "/sql/seed_exchange_rates.sql"})
  @Test
  public void shouldProperlySetExchangeRate() {
    // given
    Map<Currency, Double> ratesMap = new HashMap<>();
    double RUB_TON_RATE = 0.5;
    double RUB_BTC_RATE = 0.03;
    ratesMap.put(TON, RUB_TON_RATE);
    ratesMap.put(BTC, RUB_BTC_RATE);
    SetExchangeRateRequest request = new SetExchangeRateRequest(RUB, ratesMap);

    // when
    Map<Currency, Double> rateMapRUB = exchangeService.setRate(request);

    // then
    assertEquals(RUB_TON_RATE, rateMapRUB.get(TON));
    assertEquals(RUB_BTC_RATE, rateMapRUB.get(BTC));

    Map<Currency, Double> rateMapTON = exchangeService.getRate(TON);
    assertEquals(1 / RUB_TON_RATE, rateMapTON.get(RUB));

    Map<Currency, Double> rateMapBTC = exchangeService.getRate(BTC);
    assertEquals(1 / RUB_BTC_RATE, rateMapBTC.get(RUB));
  }
}