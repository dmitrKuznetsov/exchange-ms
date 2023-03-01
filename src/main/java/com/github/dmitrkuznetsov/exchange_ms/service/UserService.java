package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.*;
import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

public interface UserService extends UserDetailsService {

  User loadUserByUsername(String username);

  User loadUserByAuthHeader(String authHeader);

  User saveUser(User user);

  Map<Currency, Double> getBalance(String authHeader);

  Map<Currency, Double> topUpWallet(String authHeader, Money money);

  Map<Currency, Double> withdraw(String authHeader, WithdrawRequest request);

  Map<Currency, Double> withdrawCrypto(String authHeader, WithdrawCryptoRequest request);

  ConvertResponse convertAndTopUp(String authHeader, ConvertRequest request);
}
