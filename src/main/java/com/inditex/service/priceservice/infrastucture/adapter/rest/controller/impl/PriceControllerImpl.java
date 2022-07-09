package com.inditex.service.priceservice.infrastucture.adapter.rest.controller.impl;

import com.inditex.service.priceservice.application.service.PriceSearcher;
import com.inditex.service.priceservice.infrastucture.adapter.rest.controller.PriceController;
import com.inditex.service.priceservice.infrastucture.adapter.rest.response.PriceResponse;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "${api.endpoints.pricing.mapping}")
@RequiredArgsConstructor
public class PriceControllerImpl implements PriceController {

  private final PriceSearcher priceSearcher;

  @Override
  public ResponseEntity<PriceResponse> findPrice(
      Long brandId, Long productId, LocalDateTime applicationDate) {

    log.info("Calling to getPricesByCriteria {} {} {} ", brandId, productId, applicationDate);

    return priceSearcher
        .getPricesByCriteria(brandId, productId, applicationDate)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.noContent().build());
  }
}
