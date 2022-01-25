package com.playtomic.tests.wallet.shared.domain.bus.query;


import com.playtomic.tests.wallet.shared.domain.bus.query.error.QueryHandlerExecutionError;

public interface QueryBus {
    <R> R ask(Query query) throws QueryHandlerExecutionError;
}
