package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.model.AuthRequest;
import com.github.dmitrkuznetsov.exchange_ms.model.AuthResponse;
import com.github.dmitrkuznetsov.exchange_ms.model.enums.Role;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  @Override
  public AuthResponse register(AuthRequest request) {

    User user = User.builder()
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();

    userService.save(user);

    String jwtToken = jwtService.generateToken(user);

    return new AuthResponse(jwtToken);
  }

  @Override
  public AuthResponse authenticate(AuthRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );

    User user = userService.loadUserByUsername(
        request.getEmail()
    );

    if (user == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return new AuthResponse(jwtService.generateToken(user));
  }
}
