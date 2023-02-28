package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.AuthRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.AuthResponse;
import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Role;
import com.github.dmitrkuznetsov.exchange_ms.exception.UserAlreadyExistException;
import com.github.dmitrkuznetsov.exchange_ms.exception.UserNotFoundException;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.User;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  @Override
  public AuthResponse register(AuthRequest request) {

    String email = request.getEmail();

    User user = userService.loadUserByUsername(email);

    if (user == null) {
      user = User.builder()
          .email(email)
          .password(passwordEncoder.encode(request.getPassword()))
          .role(Role.USER)
          .build();

      user.addWallet(new Wallet(0, Currency.RUB, 0));
      user.addWallet(new Wallet(0, Currency.BTC, 0));
      user.addWallet(new Wallet(0, Currency.TON, 0));
    } else {
      throw new UserAlreadyExistException(email);
    }

    userService.saveUser(user);

    String jwtToken = jwtService.generateToken(user);

    return new AuthResponse(jwtToken);
  }

  @Override
  public AuthResponse authenticate(AuthRequest request) {

    String email = request.getEmail();

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            email,
            request.getPassword()
        )
    );

    User user = userService.loadUserByUsername(email);

    if (user == null)
      throw new UserNotFoundException();

    return new AuthResponse(jwtService.generateToken(user));
  }
}
