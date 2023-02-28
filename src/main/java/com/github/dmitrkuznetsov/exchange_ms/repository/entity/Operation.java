package com.github.dmitrkuznetsov.exchange_ms.repository.entity;

import com.github.dmitrkuznetsov.exchange_ms.dto.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "operation")
public class Operation {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private LocalDateTime date;

  @Enumerated(EnumType.STRING)
  private OperationType type;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User user;

  @PrePersist
  private void init() {
    date = LocalDateTime.now();
  }
}
