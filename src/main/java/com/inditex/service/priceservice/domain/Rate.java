package com.inditex.service.priceservice.domain;

import java.util.Objects;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class Rate {

  private final Long value;

  private Rate(Long value) {

    ensureIsNotNull(value);

    this.value = value;
  }

  public static Rate fromValue(Long value) {
    return new Rate(value);
  }

  private void ensureIsNotNull(Long value) {
    if (Objects.isNull(value)) {
      throw new IllegalArgumentException("Value cannot be null");
    }
  }
}
