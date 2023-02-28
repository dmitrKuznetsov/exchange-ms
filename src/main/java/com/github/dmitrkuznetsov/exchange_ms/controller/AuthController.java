package com.github.dmitrkuznetsov.exchange_ms.controller;

import com.github.dmitrkuznetsov.exchange_ms.repository.OperationRepository;
import com.github.dmitrkuznetsov.exchange_ms.repository.entity.Operation;
import com.github.dmitrkuznetsov.exchange_ms.service.AuthService;
import com.github.dmitrkuznetsov.exchange_ms.dto.AuthRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.AuthResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.dmitrkuznetsov.exchange_ms.dto.enums.OperationType.REGISTRATION;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService service;
  private final OperationRepository operationRepository;

  @PostMapping("/register")
  public AuthResponse register(
      @RequestBody @Valid AuthRequest request
  ) {

    AuthResponse response = service.register(request);

    operationRepository.save(new Operation(REGISTRATION));

    return response;
  }

  @PostMapping("/authenticate")
  public AuthResponse authenticate(
      @RequestBody @Valid AuthRequest request
  ) {

    return service.authenticate(request);
  }
}
