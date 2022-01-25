package com.playtomic.tests.wallet.application;

import com.playtomic.tests.wallet.domain.Wallet;
import com.playtomic.tests.wallet.shared.domain.bus.query.Response;

import java.util.Objects;

public class WalletResponse implements Response {
    private final String id;

    private final String current_balance;

    public WalletResponse(String id, String current_balance) {
        this.id = id;
        this.current_balance = current_balance;
    }
    public static WalletResponse fromAggregate(Wallet wallet){
        return new WalletResponse(wallet.id().value(), wallet.current_balance().value().toString());
    }
    public String id() {
        return id;
    }

    public String current_balance() {
        return current_balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletResponse that = (WalletResponse) o;
        return id.equals(that.id) && current_balance.equals(that.current_balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, current_balance);
    }
}
