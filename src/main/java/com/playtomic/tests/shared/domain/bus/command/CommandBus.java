package com.playtomic.tests.shared.domain.bus.command;

import com.playtomic.tests.shared.domain.bus.command.error.CommandHandlerExecutionError;

public interface CommandBus {

    void dispatch(Command command) throws CommandHandlerExecutionError;
}
