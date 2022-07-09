package com.inditex.service.priceservice.infrastucture.adapter.persistence.h2.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceEntityMother {

  public static PriceEntity create() {
    PriceEntity price = new PriceEntity();
    price.setPrice(BigDecimal.valueOf(35.0));
    price.setBrand(new BrandEntity(1L, "Sara"));
    price.setCurrency(new CurrencyEntity(1L, "EUR"));
    price.setProduct(new ProductEntity(1L, "Nike"));
    price.setRate(new RateEntity(1L, 1L));
    price.setStartDate(LocalDateTime.now());
    price.setEndDate(LocalDateTime.now().minusDays(2));
    return price;
  }
}
