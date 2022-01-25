package com.playtomic.tests.wallet.shared.domain.bus.command.error;

public final class CommandHandlerExecutionError extends RuntimeException {
    public CommandHandlerExecutionError(Throwable cause) {
        super(cause);
    }
}
