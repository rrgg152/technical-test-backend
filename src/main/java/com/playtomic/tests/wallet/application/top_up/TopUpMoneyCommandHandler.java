package com.playtomic.tests.wallet.application.top_up;

import com.playtomic.tests.shared.domain.Handler;
import com.playtomic.tests.shared.domain.bus.command.CommandHandler;
import com.playtomic.tests.wallet.domain.CreditCardNumer;
import com.playtomic.tests.wallet.domain.WalletAmount;
import com.playtomic.tests.wallet.domain.WalletId;
import com.playtomic.tests.wallet.domain.service.MoneyCharger;
@Handler
public class TopUpMoneyCommandHandler implements CommandHandler<TopUpMoneyCommand> {
    private final MoneyCharger charger;

    public TopUpMoneyCommandHandler(MoneyCharger charger) {
        this.charger = charger;
    }

    @Override
    public void handle(TopUpMoneyCommand command) {
        charger.charge(new WalletId(command.id()), new CreditCardNumer(command.creditCard()), new WalletAmount(command.amount()));
    }
}
