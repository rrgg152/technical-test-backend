package com.playtomic.tests.wallet.application.top_up;

import com.playtomic.tests.shared.domain.bus.command.Command;

import java.math.BigDecimal;

public class TopUpMoneyCommand implements Command {
    private final String id;

    private final BigDecimal amount;

    private final String creditCard;

    public TopUpMoneyCommand(String id, BigDecimal amount, String creditCard) {
        this.id = id;
        this.amount = amount;
        this.creditCard = creditCard;
    }
    public String id(){
        return id;
    }
    public BigDecimal amount(){
        return amount;

    }

    public String creditCard() {
        return creditCard;
    }
}
