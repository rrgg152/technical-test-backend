package com.playtomic.tests.wallet.domain.error;

import com.playtomic.tests.wallet.domain.WalletId;
import com.playtomic.tests.wallet.shared.domain.DomainError;

public class WalletNotExistError extends DomainError {
    public WalletNotExistError(WalletId id) {
        super("wallet_not_exist", String.format("The wallet %s doesn't exist", id.value()));
    }
}
