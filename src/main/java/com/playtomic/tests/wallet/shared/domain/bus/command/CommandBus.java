package com.playtomic.tests.wallet.shared.domain.bus.command;

import com.playtomic.tests.wallet.shared.domain.bus.command.error.CommandHandlerExecutionError;

public interface CommandBus {

    void dispatch(Command command) throws CommandHandlerExecutionError;
}
