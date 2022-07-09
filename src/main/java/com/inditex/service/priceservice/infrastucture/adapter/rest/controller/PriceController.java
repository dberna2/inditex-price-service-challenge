package com.inditex.service.priceservice.infrastucture.adapter.rest.controller;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.inditex.service.priceservice.infrastucture.adapter.rest.response.PriceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface PriceController {

  @Operation(
      description = "Get price by criteria.",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = PriceResponse.class))),
        @ApiResponse(
            responseCode = "204",
            description = "NO CONTENT",
            content = @Content(schema = @Schema(implementation = Void.class)))
      })
  @GetMapping(value = "/{brandId}/products/{productId}", produces = APPLICATION_JSON_VALUE)
  ResponseEntity<PriceResponse> findPrice(
      @Parameter(description = "Brand identifier", required = true) @PathVariable Long brandId,
      @Parameter(description = "Product identifier", required = true) @PathVariable Long productId,
      @Parameter(description = "Application Date", required = true)
          @RequestParam
          @DateTimeFormat(iso = DATE_TIME)
          LocalDateTime applicationDate);
}
