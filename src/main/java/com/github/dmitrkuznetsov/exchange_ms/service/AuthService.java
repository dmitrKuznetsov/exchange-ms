package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.model.AuthRequest;
import com.github.dmitrkuznetsov.exchange_ms.model.AuthResponse;

public interface AuthService {
  AuthResponse register(AuthRequest request);

  AuthResponse authenticate(AuthRequest request);
}
