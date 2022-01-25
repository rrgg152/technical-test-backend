package com.playtomic.tests.wallet.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class TopUpMoneyRequest {
    @Schema(example = "10.5", required = true)
    private final BigDecimal amount;
    @Schema(example = "4242 4242 4242 4242", required = true)
    private final String creditCard;

}
