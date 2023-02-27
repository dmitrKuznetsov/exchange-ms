package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.Fund;
import com.github.dmitrkuznetsov.exchange_ms.dto.WithdrawCryptoRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.WithdrawRequest;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

  User loadUserByUsername(String username);

  User loadUserByAuthHeader(String authHeader);

  User saveUser(User user);

  List<Fund> getBalance(String authHeader);

  List<Fund> topUpWallet(String authHeader, Fund fund);

  List<Fund> withdraw(String authHeader, WithdrawRequest request);

  List<Fund> withdrawCrypto(String authHeader, WithdrawCryptoRequest request);
}
