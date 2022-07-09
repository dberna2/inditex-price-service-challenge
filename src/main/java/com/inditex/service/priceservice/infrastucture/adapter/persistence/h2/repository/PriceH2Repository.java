package com.inditex.service.priceservice.infrastucture.adapter.persistence.h2.repository;

import com.inditex.service.priceservice.infrastucture.adapter.persistence.h2.entity.PriceEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceH2Repository extends JpaRepository<PriceEntity, Integer> {

  @Query(
      "select p from PriceEntity p "
          + "where p.product.id = :productIdentifier "
          + "and p.brand.id = :brandIdentifier "
          + "and p.startDate <= :applicationDate "
          + "and p.endDate >= :applicationDate")
  List<PriceEntity> findByBrandAndProductInRange(
      Long brandIdentifier, Long productIdentifier, LocalDateTime applicationDate);
}
