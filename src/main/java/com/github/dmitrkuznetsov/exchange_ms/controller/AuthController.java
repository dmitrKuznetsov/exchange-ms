package com.github.dmitrkuznetsov.exchange_ms.controller;

import com.github.dmitrkuznetsov.exchange_ms.service.AuthService;
import com.github.dmitrkuznetsov.exchange_ms.service.JwtService;
import com.github.dmitrkuznetsov.exchange_ms.model.AuthRequest;
import com.github.dmitrkuznetsov.exchange_ms.model.AuthResponse;
import com.github.dmitrkuznetsov.exchange_ms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService service;
  private final UserService userDetailsService;
  private final JwtService jwtService;

  @PostMapping("/register")
  public AuthResponse register(@RequestBody AuthRequest request) {

    return service.register(request);
  }

  @PostMapping("/authenticate")
  public AuthResponse authenticate(@RequestBody AuthRequest request) {

    return service.authenticate(request);
  }


}
