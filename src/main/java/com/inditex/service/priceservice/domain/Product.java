package com.inditex.service.priceservice.domain;

import java.util.Objects;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class Product {

  private final Long value;

  public Product(Long value) {

    ensureIsNotNull(value);

    this.value = value;
  }

  public static Product fromValue(Long value) {
    return new Product(value);
  }

  private void ensureIsNotNull(Long value) {
    if (Objects.isNull(value)) {
      throw new IllegalArgumentException("Value cannot be null");
    }
  }
}
