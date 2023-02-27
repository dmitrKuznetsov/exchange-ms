package com.github.dmitrkuznetsov.exchange_ms.repository.entity;

import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exchange_rate")
public class ExchangeRate {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Enumerated(EnumType.STRING)
  private Currency currencyFrom;

  @Enumerated(EnumType.STRING)
  private Currency currencyTo;

  private double rate;
}
