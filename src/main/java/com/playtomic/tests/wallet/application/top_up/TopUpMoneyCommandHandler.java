package com.playtomic.tests.wallet.application.top_up;

import com.playtomic.tests.wallet.domain.service.MoneyCharger;
import com.playtomic.tests.wallet.shared.domain.Handler;
import com.playtomic.tests.wallet.shared.domain.bus.command.CommandHandler;
@Handler
public class TopUpMoneyCommandHandler implements CommandHandler<TopUpMoneyCommand> {
    private final MoneyCharger charger;

    public TopUpMoneyCommandHandler(MoneyCharger charger) {
        this.charger = charger;
    }

    @Override
    public void handle(TopUpMoneyCommand command) {
        charger.charge(command);
    }
}
