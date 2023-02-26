package com.github.dmitrkuznetsov.exchange_ms.controller;

import com.github.dmitrkuznetsov.exchange_ms.dto.Fund;
import com.github.dmitrkuznetsov.exchange_ms.dto.WithdrawCryptoRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.WithdrawRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import com.github.dmitrkuznetsov.exchange_ms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/balance")
  public List<Fund> balance(
      @RequestHeader(name = "Authorization") String authHeader
  ) {

    return userService.getBalance(authHeader);
  }

  @PostMapping("/top-up")
  public List<Fund> topUp(
      @RequestHeader(name = "Authorization") String authHeader,
      Fund fund
  ) {

    return userService.topUpWallet(authHeader, fund);
  }

  @PostMapping("/withdraw")
  public Fund withdrawWallet(WithdrawRequest request) {

    return new Fund();
  }

  @PostMapping("/withdraw-crypto")
  public Fund withdrawWallet(WithdrawCryptoRequest request) {

    return new Fund();
  }

  @GetMapping("/exchange-rate")
  public Fund[] exchangeRate(Currency currency) {
    Fund[] funds = {};
    return funds;
  }
}
