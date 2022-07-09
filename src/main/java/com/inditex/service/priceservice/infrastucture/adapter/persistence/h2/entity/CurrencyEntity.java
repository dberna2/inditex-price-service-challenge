package com.inditex.service.priceservice.infrastucture.adapter.persistence.h2.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "currencies")
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "CODE")
  private String isoCode;
}
