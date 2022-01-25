package com.playtomic.tests.wallet.infrastructure.spring_event_handler;

import com.playtomic.tests.wallet.domain.PaymentFinishedDomainEvent;
import com.playtomic.tests.wallet.domain.service.WalletIncrementer;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UpdateWalletOnPaymentFinished {
    private final WalletIncrementer incrementer;

    public UpdateWalletOnPaymentFinished(WalletIncrementer incrementer) {
        this.incrementer = incrementer;
    }

    @Async
    @EventListener(value = PaymentFinishedDomainEvent.class)
    public void on(PaymentFinishedDomainEvent event){
        incrementer.increment(event.paymentId(), event.walletId(), event.amount());
    }
}
