package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.Fund;
import com.github.dmitrkuznetsov.exchange_ms.dto.WithdrawCryptoRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.WithdrawRequest;
import com.github.dmitrkuznetsov.exchange_ms.exception.UserNotFoundException;
import com.github.dmitrkuznetsov.exchange_ms.repository.UserRepository;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final JwtService jwtService;

  @Override
  public User loadUserByUsername(String username) {
    return userRepository.findByEmail(username).orElse(null);
  }

  @Override
  public User loadUserByAuthHeader(String authHeader) {
    return loadUserByUsername(
        jwtService.extractUsername(authHeader.substring(7))
    );
  }

  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public List<Fund> getBalance(String authHeader) {
    User user = loadUserByAuthHeader(authHeader);

    if (user == null)
      throw new UserNotFoundException();

    List<Fund> funds = user.getWallets().stream().
        map(wallet -> new Fund(wallet.getCurrency(), wallet.getCount()))
        .collect(Collectors.toList());

    return funds;
  }

  @Override
  @Transactional
  public List<Fund> topUpWallet(
      String authHeader,
      Fund payment
  ) throws UserNotFoundException {

    User user = loadUserByAuthHeader(authHeader);

    if (user == null) {
      throw new UserNotFoundException();
    }

    user.topUpWallet(payment);

    return getBalance(authHeader);
  }

  @Override
  @Transactional
  public List<Fund> withdraw(String authHeader, WithdrawRequest request) {

    User user = loadUserByAuthHeader(authHeader);

    if (user == null) {
      throw new UserNotFoundException();
    }

    user.withdraw(request.getFund());

    // ------------------------------------------
    // Some business logic for transfer operation
    // ------------------------------------------

    return getBalance(authHeader);
  }

  @Override
  @Transactional
  public List<Fund> withdrawCrypto(String authHeader, WithdrawCryptoRequest request) {
    User user = loadUserByAuthHeader(authHeader);

    if (user == null) {
      throw new UserNotFoundException();
    }

    user.withdraw(request.getFund());

    // ------------------------------------------
    // Some business logic for CRYPTO transfer operation
    // ------------------------------------------

    return getBalance(authHeader);
  }
}
