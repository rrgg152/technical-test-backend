package com.playtomic.tests.wallet.domain;

import com.playtomic.tests.shared.domain.AggregateRoot;
import com.playtomic.tests.shared.domain.StringListConverter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="WALLET")
public class Wallet extends AggregateRoot {
    @Column(name = "ID")
    @Id
    private String id;

    @Column(name = "CURRENT_BALANCE")
    private BigDecimal current_balance;

    @Convert(converter = StringListConverter.class)
    @Column(name = "PAYMENTS")
    private List<String> paymentsId;

    public Wallet(WalletId id, WalletBalance current_balance) {
        this.id = id.value();
        this.current_balance = current_balance.value();
    }

    protected Wallet() {

    }

    public WalletId id() {
        return new WalletId(id);
    }

    public WalletBalance current_balance() {
        return new WalletBalance(current_balance);
    }

    public void paymentFinished(WalletAmount amount, PaymentId paymentId) {
        record(new PaymentFinishedDomainEvent(new WalletId(id), amount, paymentId));
    }
    public void rechargeWallet(WalletAmount amount, PaymentId paymentId) {
        current_balance=current_balance.add(amount.value());
        if (this.paymentsId == null || this.paymentsId.isEmpty()){
            this.paymentsId = new ArrayList<>();
        }
        this.paymentsId.add(paymentId.value());
    }

    public boolean hasIncremented(PaymentId paymentId) {
        return paymentsId.contains(paymentId.value());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return id.equals(wallet.id) && current_balance.equals(wallet.current_balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, current_balance, paymentsId);
    }
}
