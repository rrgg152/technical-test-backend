package com.playtomic.tests.wallet.application.find;

import com.playtomic.tests.shared.domain.bus.query.Query;

import java.util.Objects;

public class FindWalletQuery implements Query {
    private final String id;

    public FindWalletQuery(String id) {
        this.id = id;
    }

    public String id() {
        return id;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindWalletQuery that = (FindWalletQuery) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
