package com.playtomic.tests.wallet.shared.domain.bus.query.error;

import com.playtomic.tests.wallet.shared.domain.DomainError;

public final class QueryHandlerExecutionError extends DomainError {
    public QueryHandlerExecutionError(Throwable cause) {
        super("QueryHandlerExecutionError", cause.getMessage());
    }
}