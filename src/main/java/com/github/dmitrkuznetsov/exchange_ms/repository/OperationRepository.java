package com.github.dmitrkuznetsov.exchange_ms.repository;

import com.github.dmitrkuznetsov.exchange_ms.repository.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
  List<Operation> findByDateAfterAndDateBefore(LocalDate dateAfter, LocalDate dateBefore);
}
