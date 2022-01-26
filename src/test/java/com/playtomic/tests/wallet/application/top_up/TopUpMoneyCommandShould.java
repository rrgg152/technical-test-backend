package com.playtomic.tests.wallet.application.top_up;

import com.playtomic.tests.wallet.domain.*;
import com.playtomic.tests.wallet.domain.service.MoneyCharger;
import com.playtomic.tests.wallet.domain.service.WalletFinder;
import com.playtomic.tests.shared.domain.UuidGenerator;
import com.playtomic.tests.shared.domain.bus.event.EventBus;
import com.playtomic.tests.shared.infrastructure.JavaUuidGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;

import static java.math.BigDecimal.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TopUpMoneyCommandShould {

    private TopUpMoneyCommandHandler handler;

    @Mock
    private PaymentGateway paymentGateway;

    @Mock
    private EventBus eventBus;

    @Mock
    private WalletFinder finder;

    @Mock
    private UuidGenerator mockedGenerator;

    private UuidGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new JavaUuidGenerator();
        MoneyCharger charger = new MoneyCharger(paymentGateway, finder, eventBus, mockedGenerator);
        handler = new TopUpMoneyCommandHandler(charger);
    }


    @Test
    public void payment_is_successful(){
        WalletId id = new WalletId(generator.generate());
        WalletBalance current_balance = new WalletBalance(valueOf(Math.random()));
        CreditCardNumer creditCard = new CreditCardNumer(generator.generate());
        String eventId = generator.generate();

        given(finder.find(id))
                .willReturn(new Wallet(id, current_balance));

        given(mockedGenerator.generate())
                .willReturn(eventId);

        BigDecimal amount = valueOf(Math.random()+10);
        TopUpMoneyCommand command = new TopUpMoneyCommand(id.value(), amount, creditCard.value());

        PaymentFinishedDomainEvent event = new PaymentFinishedDomainEvent(id, new WalletAmount(amount), new PaymentId(eventId));

        handler.handle(command);

        verify(eventBus, atLeastOnce()).publish(Collections.singletonList(event));
    }
}
