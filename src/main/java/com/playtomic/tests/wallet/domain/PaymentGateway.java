package com.playtomic.tests.wallet.domain;

public interface PaymentGateway {
    void charge(CreditCardNumer creditCard, WalletAmount amount);
}
