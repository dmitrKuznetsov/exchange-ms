package com.github.dmitrkuznetsov.exchange_ms.controller;

import com.github.dmitrkuznetsov.exchange_ms.config.JwtUtils;
import com.github.dmitrkuznetsov.exchange_ms.model.AuthRequest;
import com.github.dmitrkuznetsov.exchange_ms.model.AuthResponse;
import com.github.dmitrkuznetsov.exchange_ms.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final MyUserDetailsService userDetailsService;
  private final JwtUtils jwtUtils;

  @PostMapping("/auth")
  public AuthResponse authenticate(
      @RequestBody AuthRequest request
  ) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );

    final UserDetails userDetails = userDetailsService.loadUserByUsername(
        request.getEmail()
    );

    if (userDetails == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return new AuthResponse(jwtUtils.generateToken(userDetails));
  }

  @PostMapping("/register")
  public AuthResponse register(
      @RequestBody AuthRequest request
  ) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );

    UserDetails userDetails = userDetailsService.loadUserByUsername(
        request.getEmail()
    );

    if (userDetails != null) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    userDetails = userDetailsService.addUserDetails(request);

    return new AuthResponse(jwtUtils.generateToken(userDetails));
  }
}
