package com.playtomic.tests.wallet.domain.service;

import com.playtomic.tests.wallet.application.top_up.TopUpMoneyCommand;
import com.playtomic.tests.wallet.domain.*;
import com.playtomic.tests.wallet.shared.domain.DomainService;
import com.playtomic.tests.wallet.shared.domain.UuidGenerator;
import com.playtomic.tests.wallet.shared.domain.bus.event.EventBus;

@DomainService
public class MoneyCharger {

    private final PaymentGateway paymentGateway;

    private final WalletFinder finder;

    private final EventBus eventBus;

    private final UuidGenerator generator;

    public MoneyCharger(PaymentGateway paymentGateway, WalletFinder finder, EventBus eventBus, UuidGenerator generator) {
        this.paymentGateway = paymentGateway;
        this.finder = finder;
        this.eventBus = eventBus;
        this.generator = generator;
    }

    public void charge(TopUpMoneyCommand command){

        Wallet wallet = finder.find(new WalletId(command.id()));

        paymentGateway.charge(new CreditCardNumer(command.creditCard()), new WalletAmount(command.amount()));

        wallet.paymentFinished(new WalletAmount(command.amount()), new PaymentId(generator.generate()));

        eventBus.publish(wallet.pullDomainEvents());
    }
}
