package com.github.dmitrkuznetsov.exchange_ms.controller;

import com.github.dmitrkuznetsov.exchange_ms.dto.*;
import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import com.github.dmitrkuznetsov.exchange_ms.repository.OperationRepository;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.Operation;
import com.github.dmitrkuznetsov.exchange_ms.service.ExchangeService;
import com.github.dmitrkuznetsov.exchange_ms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.dmitrkuznetsov.exchange_ms.dto.enums.OperationType.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final ExchangeService exchangeService;
  private final OperationRepository operationRepository;

  @GetMapping("/balance")
  public List<Money> balance(
      @RequestHeader(name = "Authorization") String authHeader
  ) {

    List<Money> balance = userService.getBalance(authHeader);

    operationRepository.save(new Operation(GET_BALANCE));

    return balance;
  }

  @PostMapping("/top-up")
  public List<Money> topUp(
      @RequestHeader(name = "Authorization") String authHeader,
      @RequestBody Money money
  ) {

    List<Money> moneyList = userService.topUpWallet(authHeader, money);

    operationRepository.save(new Operation(TOP_UP));

    return moneyList;
  }

  @PostMapping("/withdraw")
  public List<Money> withdraw(
      @RequestHeader(name = "Authorization") String authHeader,
      @RequestBody WithdrawRequest request
  ) {

    List<Money> moneyList = userService.withdraw(authHeader, request);

    operationRepository.save(new Operation(WITHDRAW));

    return moneyList;
  }

  @PostMapping("/withdraw-crypto")
  public List<Money> withdrawCrypto(
      @RequestHeader(name = "Authorization") String authHeader,
      @RequestBody WithdrawCryptoRequest request) {

    List<Money> moneyList = userService.withdrawCrypto(authHeader, request);

    operationRepository.save(new Operation(WITHDRAW));

    return moneyList;
  }

  @GetMapping("/exchange-rate")
  public List<Money> getExchangeRate(
      @RequestParam("currency") Currency currency
  ) {

    List<Money> moneyList = exchangeService.getRate(currency);

    operationRepository.save(new Operation(GET_EXCHANGE_RATE));

    return moneyList;
  }

  @PostMapping("/convert")
  public ConvertResponse convert(
      @RequestHeader(name = "Authorization") String authHeader,
      @RequestBody ConvertRequest request) {

    ConvertResponse response = userService.convertAndTopUp(authHeader, request);

    operationRepository.save(new Operation(CONVERT));

    return response;
  }
}
