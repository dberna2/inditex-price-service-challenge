package com.inditex.service.priceservice.infrastucture.adapter.persistence.h2.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "prices")
@NoArgsConstructor
@AllArgsConstructor
public class PriceEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "BRAND_ID", referencedColumnName = "ID")
  private BrandEntity brand;

  @Column(name = "START_DATE")
  private LocalDateTime startDate;

  @Column(name = "END_DATE")
  private LocalDateTime endDate;

  @ManyToOne
  @JoinColumn(name = "PRICE_LIST", referencedColumnName = "ID")
  private RateEntity rate;

  @ManyToOne
  @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
  private ProductEntity product;

  @Column(name = "PRIORITY")
  private Integer priority;

  @Column(name = "PRICE")
  private BigDecimal price;

  @ManyToOne
  @JoinColumn(name = "CURR", referencedColumnName = "ID")
  private CurrencyEntity currency;
}
