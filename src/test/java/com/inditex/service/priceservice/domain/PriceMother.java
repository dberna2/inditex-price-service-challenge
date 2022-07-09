package com.inditex.service.priceservice.domain;

import java.time.LocalDateTime;

public class PriceMother {

  public static Price create() {
    return Price.builder()
        .brand(Brand.fromValue(1L))
        .rate(Rate.fromValue(1L))
        .product(Product.fromValue(1L))
        .currency(Currency.fromCode("EUR"))
        .startDate(LocalDateTime.now())
        .endDate(LocalDateTime.now().minusDays(1))
        .build();
  }
}
