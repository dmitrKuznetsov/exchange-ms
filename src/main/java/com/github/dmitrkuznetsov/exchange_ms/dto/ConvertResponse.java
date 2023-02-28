package com.github.dmitrkuznetsov.exchange_ms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConvertResponse {

  Money moneyFrom;
  Money moneyTo;
}
