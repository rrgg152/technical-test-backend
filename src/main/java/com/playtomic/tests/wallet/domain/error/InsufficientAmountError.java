package com.playtomic.tests.wallet.domain.error;

import com.playtomic.tests.wallet.shared.domain.DomainError;

import java.math.BigDecimal;

public class InsufficientAmountError extends DomainError {
    public InsufficientAmountError(BigDecimal quantity) {
        super("insufficient_amount",
                String.format("You are trying to top-up %s. The minimum value is 10", quantity));
    }
}
