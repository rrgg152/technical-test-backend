package com.playtomic.tests.wallet.api;


import com.playtomic.tests.wallet.api.dto.WalletResponse;
import com.playtomic.tests.wallet.application.find.FindWalletQuery;
import com.playtomic.tests.shared.domain.UuidGenerator;
import com.playtomic.tests.shared.domain.bus.query.QueryBus;
import com.playtomic.tests.shared.infrastructure.JavaUuidGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class WalletGetControllerShould {
    private DetailWalletGetController walletController;

    private UuidGenerator generator;
    @Mock
    private QueryBus queryBus;

    @BeforeEach
    void setUp() {
        walletController = new DetailWalletGetController(queryBus);
        generator = new JavaUuidGenerator() ;
    }
    @Test
    public void find_wallet(){
        String id = generator.generate();
        String current_balance = ""+Math.random();
        given(queryBus.ask(new FindWalletQuery(id)))
                .willReturn(
                        new com.playtomic.tests.wallet.application.WalletResponse(id, current_balance)
                );


        ResponseEntity<WalletResponse>  response = walletController.find(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .isEqualTo(WalletResponse.builder()
                        .id(id)
                        .current_balance(current_balance)
                        .build());
    }

}
