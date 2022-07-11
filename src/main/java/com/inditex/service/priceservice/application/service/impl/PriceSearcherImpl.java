package com.inditex.service.priceservice.application.service.impl;

import com.inditex.service.priceservice.application.service.PriceSearcher;
import com.inditex.service.priceservice.domain.Brand;
import com.inditex.service.priceservice.domain.Price;
import com.inditex.service.priceservice.domain.Product;
import com.inditex.service.priceservice.domain.repository.PriceRepository;
import com.inditex.service.priceservice.infrastucture.adapter.rest.response.PriceResponse;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceSearcherImpl implements PriceSearcher {

  private final PriceRepository repository;

  @Override
  public Optional<PriceResponse> getPriceByCriteria(
      Long brandId, Long productId, LocalDateTime applicationDate) {

    Product product = Product.fromValue(productId);
    Brand brand = Brand.fromValue(brandId);

    return repository.searchPricesByCriteria(brand, product, applicationDate).stream()
        .max(Comparator.comparing(Price::getPriority))
        .map(this::toPriceResponse);
  }

  private PriceResponse toPriceResponse(Price price) {
    log.info("Price retrieved is: {}", price);
    return PriceResponse.builder()
        .productId(price.getProduct().getValue())
        .brandId(price.getBrand().getValue())
        .rate(price.getRate().getValue())
        .currency(price.getCurrency().getCode())
        .startDate(price.getStartDate())
        .endDate(price.getEndDate())
        .price(price.getPrice())
        .build();
  }
}
