package com.playtomic.tests.wallet.domain.service;

import com.playtomic.tests.wallet.domain.Wallet;
import com.playtomic.tests.wallet.domain.WalletId;
import com.playtomic.tests.wallet.domain.error.WalletNotExistError;
import com.playtomic.tests.wallet.domain.WalletRepository;
import com.playtomic.tests.wallet.shared.domain.DomainService;

@DomainService
public class WalletFinder {

    private final WalletRepository repository;

    public WalletFinder(WalletRepository repo) {
        super();
        this.repository = repo;
    }

    public Wallet find(WalletId walletId) {

        return repository.find(walletId)
                .orElseThrow(() -> new WalletNotExistError(walletId));
    }
}