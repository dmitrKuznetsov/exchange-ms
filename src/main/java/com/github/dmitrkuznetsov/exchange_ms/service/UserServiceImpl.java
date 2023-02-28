package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.*;
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

  private final ExchangeService exchangeService;

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
  public List<Money> getBalance(String authHeader) {
    User user = loadUserByAuthHeader(authHeader);

    if (user == null)
      throw new UserNotFoundException();

    return user.getWallets().stream().
        map(wallet -> new Money(wallet.getCurrency(), wallet.getCount()))
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public List<Money> topUpWallet(
      String authHeader,
      Money payment
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
  public List<Money> withdraw(String authHeader, WithdrawRequest request) {

    User user = loadUserByAuthHeader(authHeader);

    if (user == null) {
      throw new UserNotFoundException();
    }

    user.withdraw(request.getMoney());

    // ------------------------------------------
    // Some business logic for transfer operation
    // ------------------------------------------

    return getBalance(authHeader);
  }

  @Override
  @Transactional
  public List<Money> withdrawCrypto(String authHeader, WithdrawCryptoRequest request) {
    User user = loadUserByAuthHeader(authHeader);

    if (user == null) {
      throw new UserNotFoundException();
    }

    user.withdraw(request.getMoney());

    // ------------------------------------------
    // Some business logic for CRYPTO transfer operation
    // ------------------------------------------

    return getBalance(authHeader);
  }

  @Override
  @Transactional
  public ConvertResponse convertAndTopUp(String authHeader, ConvertRequest request) {
    User user = loadUserByAuthHeader(authHeader);

    if (user == null) {
      throw new UserNotFoundException();
    }

    user.withdraw(request.getMoneyFrom());

    Money payment = exchangeService.convert(request);

    user.topUpWallet(payment);

    return new ConvertResponse(request.getMoneyFrom(), payment);
  }
}
