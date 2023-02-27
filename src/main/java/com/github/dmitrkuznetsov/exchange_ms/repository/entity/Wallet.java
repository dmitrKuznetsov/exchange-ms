package com.github.dmitrkuznetsov.exchange_ms.repository.entity;

import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import com.github.dmitrkuznetsov.exchange_ms.exception.NotEnoughMoney;
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
@Table(name = "wallet")
public class Wallet {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Enumerated(EnumType.STRING)
  private Currency currency;

  private int count;

  public void topUp(int payment) {
    count = count + payment;
  }

  public void withdraw(int money) {
    if (count >= money) {
      count = count - money;
    } else
      throw new NotEnoughMoney(currency);
  }
}
