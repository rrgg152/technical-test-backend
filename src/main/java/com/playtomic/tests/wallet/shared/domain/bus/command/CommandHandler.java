package com.playtomic.tests.wallet.shared.domain.bus.command;

public interface CommandHandler<T extends Command> {
    void handle(T command);
}
