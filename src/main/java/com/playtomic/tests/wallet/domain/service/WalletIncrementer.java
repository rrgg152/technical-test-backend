package com.playtomic.tests.wallet.domain.service;

import com.playtomic.tests.wallet.domain.*;
import com.playtomic.tests.wallet.shared.domain.DomainService;

@DomainService
public class WalletIncrementer {
    private final WalletRepository repository;

    private final WalletFinder finder;

    public WalletIncrementer(WalletRepository repository, WalletFinder finder) {
        this.repository = repository;
        this.finder = finder;
    }

    public void increment(PaymentId paymentId, WalletId walletId, WalletAmount amount){
        Wallet wallet=finder.find(walletId);
        if (!wallet.hasIncremented(paymentId)){
            wallet.rechargeWallet(amount, paymentId);
            repository.save(wallet);
        }

    }
}
