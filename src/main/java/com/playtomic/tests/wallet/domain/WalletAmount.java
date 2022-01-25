package com.playtomic.tests.wallet.domain;

import com.playtomic.tests.wallet.domain.error.InsufficientAmountError;

import java.math.BigDecimal;
import java.util.Objects;

public class WalletAmount {
    private final BigDecimal value;

    public BigDecimal value() {
        return value;
    }

    public WalletAmount(BigDecimal value) {
        check(value);
        this.value = value;
    }

    private void check(BigDecimal value) {
        if (0 < new BigDecimal(10).compareTo(value)) {
            throw new InsufficientAmountError(value);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletAmount that = (WalletAmount) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
