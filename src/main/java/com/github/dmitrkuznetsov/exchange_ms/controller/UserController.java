package com.github.dmitrkuznetsov.exchange_ms.controller;

import com.github.dmitrkuznetsov.exchange_ms.dto.*;
import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import com.github.dmitrkuznetsov.exchange_ms.repository.OperationRepository;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.Operation;
import com.github.dmitrkuznetsov.exchange_ms.service.ExchangeService;
import com.github.dmitrkuznetsov.exchange_ms.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.github.dmitrkuznetsov.exchange_ms.dto.enums.OperationType.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final ExchangeService exchangeService;
  private final OperationRepository operationRepository;

  @GetMapping("/balance")
  public Map<Currency, Double> balance(
      @RequestHeader(name = "Authorization") String authHeader
  ) {

    Map<Currency, Double> balance = userService.getBalance(authHeader);

    operationRepository.save(new Operation(GET_BALANCE));

    return balance;
  }

  @PostMapping("/top-up")
  public Map<Currency, Double> topUp(
      @RequestHeader(name = "Authorization") String authHeader,
      @RequestBody @Valid Money money
  ) {

    Map<Currency, Double> balance = userService.topUpWallet(authHeader, money);

    operationRepository.save(new Operation(TOP_UP));

    return balance;
  }

  @PostMapping("/withdraw")
  public Map<Currency, Double> withdraw(
      @RequestHeader(name = "Authorization") String authHeader,
      @RequestBody @Valid WithdrawRequest request
  ) {

    Map<Currency, Double> balance = userService.withdraw(authHeader, request);

    operationRepository.save(new Operation(WITHDRAW));

    return balance;
  }

  @PostMapping("/withdraw-crypto")
  public Map<Currency, Double> withdrawCrypto(
      @RequestHeader(name = "Authorization") String authHeader,
      @RequestBody @Valid WithdrawCryptoRequest request) {

    Map<Currency, Double> balance = userService.withdrawCrypto(authHeader, request);

    operationRepository.save(new Operation(WITHDRAW));

    return balance;
  }

  @GetMapping("/exchange-rate")
  public Map<Currency, Double> getExchangeRate(
      @RequestParam("currency") Currency currency
  ) {

    Map<Currency, Double> rates = exchangeService.getRate(currency);

    operationRepository.save(new Operation(GET_EXCHANGE_RATE));

    return rates;
  }

  @PostMapping("/convert")
  public ConvertResponse convert(
      @RequestHeader(name = "Authorization") String authHeader,
      @RequestBody @Valid ConvertRequest request) {

    ConvertResponse response = userService.convertAndTopUp(authHeader, request);

    operationRepository.save(new Operation(CONVERT));

    return response;
  }
}
