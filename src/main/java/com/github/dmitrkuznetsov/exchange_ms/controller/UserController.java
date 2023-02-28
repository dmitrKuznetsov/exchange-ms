package com.github.dmitrkuznetsov.exchange_ms.controller;

import com.github.dmitrkuznetsov.exchange_ms.dto.*;
import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import com.github.dmitrkuznetsov.exchange_ms.service.ExchangeService;
import com.github.dmitrkuznetsov.exchange_ms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  private final ExchangeService exchangeService;

  @GetMapping("/balance")
  public List<Fund> balance(
      @RequestHeader(name = "Authorization") String authHeader
  ) {

    return userService.getBalance(authHeader);
  }

  @PostMapping("/top-up")
  public List<Fund> topUp(
      @RequestHeader(name = "Authorization") String authHeader,
      @RequestBody Fund fund
  ) {

    return userService.topUpWallet(authHeader, fund);
  }

  @PostMapping("/withdraw")
  public List<Fund> withdraw(
      @RequestHeader(name = "Authorization") String authHeader,
      @RequestBody WithdrawRequest request
  ) {

    return userService.withdraw(authHeader, request);
  }

  @PostMapping("/withdraw-crypto")
  public List<Fund> withdrawCrypto(
      @RequestHeader(name = "Authorization") String authHeader,
      @RequestBody WithdrawCryptoRequest request) {

    return userService.withdrawCrypto(authHeader, request);
  }

  @GetMapping("/exchange-rate")
  public List<Fund> exchangeRate(
      @RequestParam("currency") Currency currency
  ) {

    return exchangeService.getRate(currency);
  }

  @PostMapping("/convert")
  public ConvertResponse convert(
      @RequestHeader(name = "Authorization") String authHeader,
      @RequestBody ConvertRequest request) {

    return userService.convertAndTopUp(authHeader, request);
  }
}
