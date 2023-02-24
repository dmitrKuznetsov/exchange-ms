package com.github.dmitrkuznetsov.exchange_ms.controller;

import com.github.dmitrkuznetsov.exchange_ms.config.JwtUtils;
import com.github.dmitrkuznetsov.exchange_ms.model.AuthRequest;
import com.github.dmitrkuznetsov.exchange_ms.model.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;
  private final JwtUtils jwtUtils;

  @PostMapping("/auth")
  public ResponseEntity<String> authenticate(
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

    if (userDetails != null) {
      return ResponseEntity.ok(jwtUtils.generateToken(userDetails));
    }

    return ResponseEntity.status(400).body("Some error has occurred");
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(
      @RequestBody RegisterRequest request
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

    if (userDetails != null) {
      return ResponseEntity.ok(jwtUtils.generateToken(userDetails));
    }

    return ResponseEntity.status(400).body("Some error has occurred");
  }
}
