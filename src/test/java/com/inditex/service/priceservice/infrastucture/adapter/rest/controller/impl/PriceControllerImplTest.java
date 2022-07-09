package com.inditex.service.priceservice.infrastucture.adapter.rest.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;

import com.inditex.service.priceservice.domain.ProductMother;
import com.inditex.service.priceservice.infrastucture.adapter.rest.response.PriceResponse;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PriceControllerImplTest {

  private static final String BASE_PATH = "http://localhost:8080";
  private static final String GET_PRICE_PATH =
      "/pricing/{brandId}/products/{productId}?applicationDate=";

  public static final BigDecimal PRICE1 = BigDecimal.valueOf(35.50);
  public static final BigDecimal PRICE2 = BigDecimal.valueOf(25.45);
  public static final BigDecimal PRICE3 = BigDecimal.valueOf(30.50);
  public static final BigDecimal PRICE4 = BigDecimal.valueOf(38.95);

  @Autowired private TestRestTemplate restTemplate;

  @BeforeEach
  void setUp() {}

  @ParameterizedTest
  @MethodSource("postConditionsAssertGenerator")
  void shouldReturnActualPriceForBrandProductAnfFilterDateAndHttpStatusCode200(
      String applicationDate, BigDecimal expectedPrice) {

    Long brandId = 1L;
    Long productId = ProductMother.defaultProductId();

    PriceResponse price =
        restTemplate.getForObject(
            BASE_PATH + GET_PRICE_PATH + applicationDate, PriceResponse.class, brandId, productId);

    assertThat(price).isNotNull();
    assertThat(price.getPrice()).isEqualByComparingTo(expectedPrice);
  }

  @Test
  void shouldReturnEmptyPriceResponseWhenProductIdNotFoundAndHttpStatusCode204() {

    Long brandId = 1L;
    Long productId = ProductMother.invalidProductId();
    String applicationDate = "2020-06-14T10:00:00.000Z";

    PriceResponse price =
        restTemplate.getForObject(
            BASE_PATH + GET_PRICE_PATH + applicationDate, PriceResponse.class, brandId, productId);

    assertThat(price).isNull();
  }

  private static Stream<Arguments> postConditionsAssertGenerator() {
    return Stream.of(
        Arguments.of("2020-06-14T10:00:00.000Z", PRICE1),
        Arguments.of("2020-06-14T16:00:00.000Z", PRICE2),
        Arguments.of("2020-06-14T21:00:00.000Z", PRICE1),
        Arguments.of("2020-06-15T10:00:00.000Z", PRICE3),
        Arguments.of("2020-06-16T21:00:00.000Z", PRICE4));
  }
}
