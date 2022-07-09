package com.inditex.service.priceservice.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public final class Price {

  private final Brand brand;
  private final LocalDateTime startDate;
  private final LocalDateTime endDate;
  private final Rate rate;
  private final Product product;
  private final Integer priority;
  private final BigDecimal price;
  private final Currency currency;
}
