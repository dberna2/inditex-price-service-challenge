package com.inditex.service.priceservice.domain;

public class ProductMother {

  public static Long defaultProductId() {
    return 35455L;
  }

  public static Long invalidProductId() {
    return 1L;
  }

  public static Product create() {
    return Product.fromValue(defaultProductId());
  }
}
