package com.inditex.service.priceservice.domain.repository;

import com.inditex.service.priceservice.domain.Brand;
import com.inditex.service.priceservice.domain.Price;
import com.inditex.service.priceservice.domain.Product;
import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {

  List<Price> searchByCriteria(Brand brand, Product product, LocalDateTime applicationDate);
}
