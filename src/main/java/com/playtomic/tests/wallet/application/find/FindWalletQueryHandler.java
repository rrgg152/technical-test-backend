package com.playtomic.tests.wallet.application.find;

import com.playtomic.tests.wallet.application.WalletResponse;
import com.playtomic.tests.wallet.domain.WalletId;
import com.playtomic.tests.wallet.domain.error.WalletNotExistError;
import com.playtomic.tests.wallet.domain.service.WalletFinder;
import com.playtomic.tests.shared.domain.Handler;
import com.playtomic.tests.shared.domain.bus.query.QueryHandler;

@Handler
public final class FindWalletQueryHandler implements QueryHandler<FindWalletQuery, WalletResponse> {

    private final WalletFinder finder;

    public FindWalletQueryHandler(WalletFinder finder) {
        this.finder = finder;
    }

    @Override
    public WalletResponse handle(FindWalletQuery query) throws WalletNotExistError {
        return WalletResponse.fromAggregate(
                finder.find(new WalletId(query.id())));
    }
}