package com.inditex.service.priceservice.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class Currency {
  private static final String CURRENCY_FORMAT = "^[A-Z]{3}$";
  private final String code;

  private Currency(String code) {

    ensureIsValidCode(code);

    this.code = code;
  }

  public static Currency fromCode(String code) {
    return new Currency(code);
  }

  private void ensureIsValidCode(String code) {
    if (!code.matches(CURRENCY_FORMAT)) {
      throw new IllegalArgumentException("Invalid currency code");
    }
  }
}
