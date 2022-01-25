package com.playtomic.tests.wallet.shared.domain.bus.query.error;


import com.playtomic.tests.wallet.shared.domain.bus.query.Query;

public final class QueryNotRegisteredError extends Exception {
    public QueryNotRegisteredError(Class<? extends Query> query) {
        super(String.format("The query <%s> hasn't a query handler associated", query.toString()));
    }
}