package com.github.dmitrkuznetsov.exchange_ms.repository;

import com.github.dmitrkuznetsov.exchange_ms.repository.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Integer> {
}
