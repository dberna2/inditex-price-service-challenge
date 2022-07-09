package com.inditex.service.priceservice.application.service;

import com.inditex.service.priceservice.infrastucture.adapter.rest.response.PriceResponse;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceSearcher {

  Optional<PriceResponse> byCriteria(Long brandId, Long productId, LocalDateTime applicationDate);
}
