package com.inditex.service.priceservice.infrastucture.adapter.persistence.h2.repository.impl;

import com.inditex.service.priceservice.domain.Brand;
import com.inditex.service.priceservice.domain.Currency;
import com.inditex.service.priceservice.domain.Price;
import com.inditex.service.priceservice.domain.Product;
import com.inditex.service.priceservice.domain.Rate;
import com.inditex.service.priceservice.domain.repository.PriceRepository;
import com.inditex.service.priceservice.infrastucture.adapter.persistence.h2.entity.PriceEntity;
import com.inditex.service.priceservice.infrastucture.adapter.persistence.h2.repository.PriceH2Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InMemoryPriceRepositoryImpl implements PriceRepository {

  private final PriceH2Repository repository;

  @Override
  public List<Price> searchByCriteria(Brand brand, Product product, LocalDateTime applicationDate) {

    Long productIdentifier = product.getValue();
    Long brandIdentifier = brand.getValue();

    return repository
        .findByBrandAndProductInRange(brandIdentifier, productIdentifier, applicationDate)
        .stream()
        .map(this::fromEntity)
        .collect(Collectors.toList());
  }

  private Price fromEntity(PriceEntity entity) {
    return Price.builder()
        .brand(Brand.fromValue(entity.getBrand().getId()))
        .rate(Rate.fromValue(entity.getRate().getValue()))
        .product(Product.fromValue(entity.getProduct().getId()))
        .currency(Currency.fromCode(entity.getCurrency().getIsoCode()))
        .price(entity.getPrice())
        .priority(entity.getPriority())
        .startDate(entity.getStartDate())
        .endDate(entity.getEndDate())
        .build();
  }
}
