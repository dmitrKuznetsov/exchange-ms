package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.dto.AuthRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.AuthResponse;
import com.github.dmitrkuznetsov.exchange_ms.exception.UserAlreadyExistException;
import com.github.dmitrkuznetsov.exchange_ms.exception.UserNotFoundException;

public interface AuthService {
  AuthResponse register(AuthRequest request) throws UserAlreadyExistException;

  AuthResponse authenticate(AuthRequest request) throws UserNotFoundException;
}
