package com.inditex.service.priceservice.infrastucture.adapter.rest.controller.impl;

import com.inditex.service.priceservice.application.service.PriceSearcher;
import com.inditex.service.priceservice.infrastucture.adapter.rest.controller.PriceController;
import com.inditex.service.priceservice.infrastucture.adapter.rest.response.PriceResponse;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.endpoints.pricing.mapping}")
@RequiredArgsConstructor
public class PriceControllerImpl implements PriceController {

  private final PriceSearcher priceSearcher;

  @Override
  public ResponseEntity<PriceResponse> findPrice(
      Long brandId, Long productId, LocalDateTime applicationDate) {

    return priceSearcher
        .byCriteria(brandId, productId, applicationDate)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.noContent().build());
  }
}
