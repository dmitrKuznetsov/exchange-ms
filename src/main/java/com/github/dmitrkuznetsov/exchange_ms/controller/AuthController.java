package com.github.dmitrkuznetsov.exchange_ms.controller;

import com.github.dmitrkuznetsov.exchange_ms.service.AuthService;
import com.github.dmitrkuznetsov.exchange_ms.dto.AuthRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.AuthResponse;
import jakarta.validation.Valid;
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

  @PostMapping("/register")
  public AuthResponse register(
      @RequestBody @Valid AuthRequest request
  ) {

    return service.register(request);
  }

  @PostMapping("/authenticate")
  public AuthResponse authenticate(
      @RequestBody @Valid AuthRequest request
  ) {

    return service.authenticate(request);
  }


}
