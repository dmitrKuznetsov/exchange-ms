package com.github.dmitrkuznetsov.exchange_ms.controller;

import com.github.dmitrkuznetsov.exchange_ms.model.Fund;
import com.github.dmitrkuznetsov.exchange_ms.model.WithdrawCryptoRequest;
import com.github.dmitrkuznetsov.exchange_ms.model.WithdrawRequest;
import com.github.dmitrkuznetsov.exchange_ms.model.enums.Currency;
import com.github.dmitrkuznetsov.exchange_ms.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

//  private final UserWalletService userWalletService;
  private final JwtService jwtService;

  @GetMapping("/balance")
  public String balanceWallet(@RequestHeader(name = "Authorization") String authHeader) {
    Fund[] funds = {};
    return jwtService.handleAuthHeader(authHeader);
  }

  @PostMapping("/top-up")
  public Fund topUpWallet(Fund fund) {
    return fund;
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
