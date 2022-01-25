package com.playtomic.tests.wallet.api;

import com.playtomic.tests.wallet.api.dto.TopUpMoneyRequest;
import com.playtomic.tests.wallet.application.top_up.TopUpMoneyCommand;
import com.playtomic.tests.wallet.shared.domain.bus.command.Command;
import com.playtomic.tests.wallet.shared.domain.bus.command.CommandBus;
import com.playtomic.tests.wallet.shared.domain.bus.command.error.CommandHandlerExecutionError;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopUpWalletPutController {
    final CommandBus commandBus;

    public TopUpWalletPutController(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PutMapping("/wallet/{id}")
    public ResponseEntity<HttpStatus> topUpMoney(@Schema(example = "a7f16aa2-158d-11ec-82a8-0242ac130003", required = true) @PathVariable String id,
                                                 @RequestBody TopUpMoneyRequest topUpMoneyRequest) throws CommandHandlerExecutionError {
        dispatch(commandBus, new TopUpMoneyCommand(id, topUpMoneyRequest.getAmount(), topUpMoneyRequest.getCreditCard()));

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    protected void dispatch(CommandBus commandBus, Command command) throws CommandHandlerExecutionError {
        commandBus.dispatch(command);
    }
}