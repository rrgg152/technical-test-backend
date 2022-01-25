package com.playtomic.tests.wallet.shared.domain.bus.query;


public interface QueryHandler<Q extends Query, R extends Response> {
    R handle(Q query);
}