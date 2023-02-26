package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.Fund;
import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import com.github.dmitrkuznetsov.exchange_ms.exception.UserNotFoundException;
import com.github.dmitrkuznetsov.exchange_ms.repository.UserRepository;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  //todo database
  private final Map<String, List<Fund>> walletsMap = new HashMap<>();
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

  public User saveUser(User user) {
    List<Fund> funds = Arrays.asList(
        new Fund(Currency.RUB, 0),
        new Fund(Currency.BTC, 0),
        new Fund(Currency.TON, 0)
    );

    walletsMap.put(user.getEmail(), funds);
    return userRepository.save(user);
  }

  @Override
  public List<Fund> getBalance(String authHeader) {
    User user = loadUserByAuthHeader(authHeader);

    if (user == null)
      throw new UserNotFoundException();

    return walletsMap.get(user.getEmail());
  }

  @Override
  public List<Fund> topUpWallet(String authHeader, Fund payment) throws UserNotFoundException {

    User user = loadUserByAuthHeader(authHeader);

    if (user == null) {
      throw new UserNotFoundException();
    }

    String email = user.getEmail();

    List<Fund> balance = walletsMap.get(email);

    Fund wallet = balance.stream()
        .filter(balanceEl ->
            balanceEl.getCurrency().equals(payment.getCurrency())
        )
        .findFirst()
        .orElse(null);

    if (wallet == null) {
      balance.add(payment);
    } else {
      wallet.setCount(wallet.getCount() + payment.getCount());
    }

    return walletsMap.get(email);
  }
}
