package com.playtomic.tests.wallet.domain;

import com.playtomic.tests.wallet.domain.error.InvalidUUIDFormatError;

import java.util.Objects;
import java.util.UUID;

public class PaymentId {
    private final UUID value;

    public PaymentId(String value) {
        ensureValidUuid(value);
        this.value = UUID.fromString(value);
    }
    public String value(){
        return this.value.toString();
    }
    public static void ensureValidUuid(String value) throws InvalidUUIDFormatError {
        try{
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new InvalidUUIDFormatError(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentId paymentId = (PaymentId) o;
        return value.equals(paymentId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
