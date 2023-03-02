package com.github.dmitrkuznetsov.exchange_ms.repository;

import com.github.dmitrkuznetsov.exchange_ms.repository.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer> {
  List<Operation> findByDateBetween(LocalDate dateAfter, LocalDate dateBefore);
}
