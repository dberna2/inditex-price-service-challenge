package com.inditex.service.priceservice.infrastucture.adapter.persistence.h2.repository.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.inditex.service.priceservice.domain.Brand;
import com.inditex.service.priceservice.domain.BrandMother;
import com.inditex.service.priceservice.domain.Price;
import com.inditex.service.priceservice.domain.Product;
import com.inditex.service.priceservice.domain.ProductMother;
import com.inditex.service.priceservice.infrastucture.adapter.persistence.h2.entity.PriceEntity;
import com.inditex.service.priceservice.infrastucture.adapter.persistence.h2.entity.PriceEntityMother;
import com.inditex.service.priceservice.infrastucture.adapter.persistence.h2.repository.PriceH2Repository;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InMemoryPriceRepositoryImplTest {
  @Mock PriceH2Repository h2Repository;
  @InjectMocks InMemoryPriceRepositoryImpl inMemoryPriceRepositoryImpl;

  @Test
  void shouldRetrieveAllPricesByBrandProductAndFilterDate() {

    PriceEntity priceEntity = PriceEntityMother.create();

    when(h2Repository.findByBrandAndProductInRange(anyLong(), anyLong(), any(LocalDateTime.class)))
        .thenReturn(List.of(priceEntity));

    Brand brand = BrandMother.create();
    Product product = ProductMother.create();
    LocalDateTime applicationDate = LocalDateTime.now();

    List<Price> result =
        inMemoryPriceRepositoryImpl.searchPricesByCriteria(brand, product, applicationDate);

    verify(h2Repository, times(1))
        .findByBrandAndProductInRange(brand.getValue(), product.getValue(), applicationDate);

    assertThat(result).hasSize(1);
  }

  @Test
  void shouldRetrieveEmptyPricesByBrandProductAndFilterDate() {

    when(h2Repository.findByBrandAndProductInRange(anyLong(), anyLong(), any(LocalDateTime.class)))
        .thenReturn(Collections.emptyList());

    Brand brand = BrandMother.create();
    Product product = ProductMother.create();
    LocalDateTime applicationDate = LocalDateTime.now().plusMonths(3);

    List<Price> result =
        inMemoryPriceRepositoryImpl.searchPricesByCriteria(brand, product, applicationDate);

    verify(h2Repository, times(1))
        .findByBrandAndProductInRange(brand.getValue(), product.getValue(), applicationDate);

    assertThat(result).isEmpty();
  }
}
