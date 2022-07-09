package com.inditex.service.priceservice.application.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.inditex.service.priceservice.domain.Brand;
import com.inditex.service.priceservice.domain.BrandMother;
import com.inditex.service.priceservice.domain.Price;
import com.inditex.service.priceservice.domain.PriceMother;
import com.inditex.service.priceservice.domain.Product;
import com.inditex.service.priceservice.domain.ProductMother;
import com.inditex.service.priceservice.domain.repository.PriceRepository;
import com.inditex.service.priceservice.infrastucture.adapter.rest.response.PriceResponse;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceSearcherByCriteriaImplTest {
  @Mock PriceRepository repository;
  @InjectMocks PriceSearcherImpl priceSearcher;

  @Test
  void shouldReturnCorrectPriceByBrandAndProductIdAndApplicationDate() {

    Price price = PriceMother.create();
    List<Price> prices = List.of(price);

    when(repository.searchPricesByCriteria(
            any(Brand.class), any(Product.class), any(LocalDateTime.class)))
        .thenReturn(prices);

    long brandId = BrandMother.defaultBrandId();
    long productId = ProductMother.defaultProductId();
    LocalDateTime applicationDate = LocalDateTime.now();

    Optional<PriceResponse> result =
        priceSearcher.getPricesByCriteria(brandId, productId, applicationDate);

    assertThat(result).isPresent();
    assertThat(result.get().getPrice()).isEqualTo(price.getPrice());
    assertThat(result.get().getCurrency()).isEqualTo(price.getCurrency().getCode());
    assertThat(result.get().getStartDate()).isEqualTo(price.getStartDate());
    assertThat(result.get().getEndDate()).isEqualTo(price.getEndDate());
  }

  @Test
  void shouldReturnEmptyResponseWhenBrandIdNotMatch() {

    List<Price> prices = Collections.emptyList();

    when(repository.searchPricesByCriteria(
            any(Brand.class), any(Product.class), any(LocalDateTime.class)))
        .thenReturn(prices);

    long brandId = BrandMother.defaultBrandId();
    long productId = ProductMother.defaultProductId();
    LocalDateTime applicationDate = LocalDateTime.now();

    Optional<PriceResponse> result =
        priceSearcher.getPricesByCriteria(brandId, productId, applicationDate);

    assertThat(result).isNotPresent();
  }

  @Test
  void shouldReturnEmptyResponseWhenProductIdNotMatch() {

    List<Price> prices = Collections.emptyList();

    when(repository.searchPricesByCriteria(
            any(Brand.class), any(Product.class), any(LocalDateTime.class)))
        .thenReturn(prices);

    long brandId = BrandMother.defaultBrandId();
    long productId = ProductMother.defaultProductId();
    LocalDateTime applicationDate = LocalDateTime.now();

    Optional<PriceResponse> result =
        priceSearcher.getPricesByCriteria(brandId, productId, applicationDate);

    assertThat(result).isNotPresent();
  }

  @Test
  void shouldReturnEmptyResponseWhenApplicationDateIsNotInRange() {

    List<Price> prices = Collections.emptyList();

    when(repository.searchPricesByCriteria(
            any(Brand.class), any(Product.class), any(LocalDateTime.class)))
        .thenReturn(prices);

    long brandId = BrandMother.defaultBrandId();
    long productId = ProductMother.defaultProductId();
    LocalDateTime applicationDate = LocalDateTime.now().plusMonths(2);

    Optional<PriceResponse> result =
        priceSearcher.getPricesByCriteria(brandId, productId, applicationDate);

    assertThat(result).isNotPresent();
  }
}
