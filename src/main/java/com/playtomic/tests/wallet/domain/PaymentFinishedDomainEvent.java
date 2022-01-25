package com.playtomic.tests.wallet.domain;

import com.playtomic.tests.wallet.shared.domain.bus.event.DomainEvent;

import java.util.Objects;

public class PaymentFinishedDomainEvent extends DomainEvent {
    private final WalletAmount amount;
    private final PaymentId paymentId;

    public PaymentFinishedDomainEvent(WalletId id, WalletAmount amount, PaymentId paymentId) {
        super(id.value());
        this.amount = amount;
        this.paymentId = paymentId;
    }

    @Override
    public String eventName() {
        return "wallet.charged";
    }
    public WalletAmount amount(){
        return amount;
    }

    public PaymentId paymentId() {
        return paymentId;
    }

    public WalletId walletId() {
        return new WalletId(this.aggregateId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentFinishedDomainEvent that = (PaymentFinishedDomainEvent) o;
        return amount.equals(that.amount) && paymentId.equals(that.paymentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, paymentId);
    }
}
