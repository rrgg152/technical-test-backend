package com.playtomic.tests.wallet.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class WalletBalance {
    private final BigDecimal value;

    public BigDecimal value() {
        return value;
    }

    public WalletBalance(BigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletBalance that = (WalletBalance) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
