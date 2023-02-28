package com.github.dmitrkuznetsov.exchange_ms.controller;

import com.github.dmitrkuznetsov.exchange_ms.dto.Money;
import com.github.dmitrkuznetsov.exchange_ms.dto.OperationCountResponse;
import com.github.dmitrkuznetsov.exchange_ms.dto.SetExchangeRateRequest;
import com.github.dmitrkuznetsov.exchange_ms.dto.enums.Currency;
import com.github.dmitrkuznetsov.exchange_ms.service.ExchangeService;
import com.github.dmitrkuznetsov.exchange_ms.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

  private final ExchangeService exchangeService;
  private final AdminService adminService;

  @PostMapping("/exchange-rate")
  public List<Money> setExchangeRate(
      @RequestBody SetExchangeRateRequest request
  ) {

    return exchangeService.setRate(request);
  }

  @GetMapping("/total-money")
  public Money totalMoney(
      @RequestParam("currency") Currency currency
  ) {

    return adminService.getTotalMoney(currency);
  }

  @GetMapping("/operation-count")
  public OperationCountResponse operationCount(
      @RequestParam("dateFrom")
      @DateTimeFormat(pattern = "dd:mm:yyyy")
      Date dateFrom,
      @RequestParam("dateTo")
      @DateTimeFormat(pattern = "dd:mm:yyyy")
      Date dateTo
  ) {



    LocalDate localDateFrom = dateFrom.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();

    LocalDate localDateTo = dateTo.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();

    System.out.println(localDateFrom);
    System.out.println(localDateTo);

    int count = adminService.getOperationCount(localDateFrom, localDateTo);

    return new OperationCountResponse(count);
  }
}
