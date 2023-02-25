package com.github.dmitrkuznetsov.exchange_ms.service;

import com.github.dmitrkuznetsov.exchange_ms.model.AuthRequest;
import com.github.dmitrkuznetsov.exchange_ms.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsServiceImpl implements MyUserDetailsService {

  private final UserDetailsRepository userDetailsRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    return userDetailsRepository.loadUserByEmail(username);
  }

  @Override
  public UserDetails addUserDetails(AuthRequest authRequest) {
    return userDetailsRepository.addUserDetails(authRequest);
  }
}
