package com.github.dmitrkuznetsov.exchange_ms.repository;

import com.github.dmitrkuznetsov.exchange_ms.repository.entity.Operation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class OperationRepositoryTest {

  @Autowired
  private OperationRepository operationRepository;

  @Sql(scripts = {"/sql/clear_dbs.sql", "/sql/seed_operations.sql"})
  @Test
  public void shouldProperlyReturnCountOperations() {
    // given
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
    List<String> dates = List.of("27:02:2023", "28:02:2023", "01:03:2023", "02:03:2023");
    List<Integer> counts = List.of(0, 4, 5, 3);

    for (int ii = 0; ii < dates.size(); ii++) {
      LocalDate date = LocalDate.parse(dates.get(ii), formatter);

      // when
      List<Operation> operations = operationRepository.findByDateBetween(date, date);

      // then
      assertEquals(operations.size(), counts.get(ii));
    }
  }
}