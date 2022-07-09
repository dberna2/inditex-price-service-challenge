package com.inditex.service.priceservice.domain;

public class BrandMother {

  public static Brand create() {
    return Brand.fromValue(defaultBrandId());
  }

  public static Long defaultBrandId() {
    return 1L;
  }
}
