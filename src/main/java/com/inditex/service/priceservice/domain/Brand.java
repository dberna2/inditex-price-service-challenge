package com.inditex.service.priceservice.domain;

import java.util.Objects;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class Brand {

  private final Long value;

  private Brand(Long value) {

    ensureIsNotNull(value);

    this.value = value;
  }

  public static Brand fromValue(Long value) {
    return new Brand(value);
  }

  private void ensureIsNotNull(Long value) {
    if (Objects.isNull(value)) {
      throw new IllegalArgumentException("Value cannot be null");
    }
  }
}
