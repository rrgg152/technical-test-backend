package com.playtomic.tests.shared.infrastructure.bus.command;
import com.playtomic.tests.shared.domain.bus.command.CommandHandler;
import com.playtomic.tests.shared.domain.bus.command.Command;
import com.playtomic.tests.shared.domain.bus.command.CommandBus;
import com.playtomic.tests.shared.domain.bus.command.error.CommandHandlerExecutionError;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


@Service
public final class InMemoryCommandBus implements CommandBus {
    private final CommandHandlersInformation information;
    private final ApplicationContext         context;

    public InMemoryCommandBus(CommandHandlersInformation information, ApplicationContext context) {
        this.information = information;
        this.context     = context;
    }

    @Override
    public void dispatch(Command command) throws CommandHandlerExecutionError {
        try {
            Class<? extends CommandHandler> commandHandlerClass = information.search(command.getClass());

            CommandHandler handler = context.getBean(commandHandlerClass);

            handler.handle(command);
        } catch (Throwable error) {
            throw new CommandHandlerExecutionError(error);
        }
    }
}