package com.inditex.service.priceservice.infrastucture.adapter.rest.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class PriceResponse {

  @Schema(required = true, description = "Product identifier", example = "35455")
  private Long productId;

  @Schema(required = true, description = "Brand identifier", example = "1")
  private Long brandId;

  @Schema(required = true, description = "Rate related to a price", example = "1")
  private Long rate;

  @Schema(required = true, description = "Prive amount", example = "30.50")
  private BigDecimal price;

  @Schema(required = true, description = "Price currency", example = "EUR")
  private String currency;

  @Schema(required = true, description = "Price start date", example = "2020-06-15 16.00.00")
  private LocalDateTime startDate;

  @Schema(required = true, description = "Price end date", example = "2020-12-31 23.59.59")
  private LocalDateTime endDate;
}
