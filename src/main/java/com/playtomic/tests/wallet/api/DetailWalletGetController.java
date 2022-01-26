package com.playtomic.tests.wallet.api;

import com.playtomic.tests.wallet.api.dto.WalletResponse;
import com.playtomic.tests.wallet.application.find.FindWalletQuery;
import com.playtomic.tests.shared.domain.bus.query.Query;
import com.playtomic.tests.shared.domain.bus.query.QueryBus;
import com.playtomic.tests.shared.domain.bus.query.error.QueryHandlerExecutionError;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DetailWalletGetController {
    private final Logger log = LoggerFactory.getLogger(DetailWalletGetController.class);

    private final QueryBus queryBus;

    public DetailWalletGetController(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @GetMapping("/wallet/{id}")
    public ResponseEntity<WalletResponse> find(@Schema(example = "a7f16aa2-158d-11ec-82a8-0242ac130003", required = true) @PathVariable String id) throws QueryHandlerExecutionError {
        com.playtomic.tests.wallet.application.WalletResponse response = ask(queryBus, new FindWalletQuery(id));
        log.debug(String.format("wallet %s found!", id));
        return ResponseEntity.ok(mapToWalletDto(response));
    }


    private WalletResponse mapToWalletDto(com.playtomic.tests.wallet.application.WalletResponse response) {
        return WalletResponse.builder()
                .id(response.id())
                .current_balance(response.current_balance())
                .build();
    }

    protected <R> R ask(QueryBus queryBus, Query query) throws QueryHandlerExecutionError {
        return queryBus.ask(query);
    }
}
