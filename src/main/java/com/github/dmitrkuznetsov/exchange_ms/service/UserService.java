package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.*;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

  User loadUserByUsername(String username);

  User loadUserByAuthHeader(String authHeader);

  User saveUser(User user);

  List<Money> getBalance(String authHeader);

  List<Money> topUpWallet(String authHeader, Money money);

  List<Money> withdraw(String authHeader, WithdrawRequest request);

  List<Money> withdrawCrypto(String authHeader, WithdrawCryptoRequest request);

  ConvertResponse convertAndTopUp(String authHeader, ConvertRequest request);
}
