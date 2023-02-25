package com.github.dmitrkuznetsov.exchange_ms.controller;

import com.github.dmitrkuznetsov.exchange_ms.model.Fund;
import com.github.dmitrkuznetsov.exchange_ms.model.WithdrawCryptoRequest;
import com.github.dmitrkuznetsov.exchange_ms.model.WithdrawRequest;
import com.github.dmitrkuznetsov.exchange_ms.model.enums.Currency;
import com.github.dmitrkuznetsov.exchange_ms.service.UserWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserWalletService userWalletService;

  @GetMapping("/balance")
  public Fund[] balanceWallet() {
    Fund[] funds = {};
    return funds;
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
