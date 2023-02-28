package com.github.dmitrkuznetsov.exchange_ms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConvertResponse {

  Fund fundFrom;
  Fund fundTo;
}
