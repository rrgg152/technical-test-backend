package com.playtomic.tests.wallet.application.find;

import com.playtomic.tests.wallet.application.WalletResponse;
import com.playtomic.tests.wallet.domain.Wallet;
import com.playtomic.tests.wallet.domain.WalletBalance;
import com.playtomic.tests.wallet.domain.WalletId;
import com.playtomic.tests.wallet.domain.WalletRepository;
import com.playtomic.tests.wallet.domain.error.WalletNotExistError;
import com.playtomic.tests.wallet.domain.service.WalletFinder;
import com.playtomic.tests.shared.domain.UuidGenerator;
import com.playtomic.tests.shared.infrastructure.JavaUuidGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class FindWalletQueryHandlerShould {

    private FindWalletQueryHandler handler;
    @Mock
    private WalletRepository repository;

    private UuidGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new JavaUuidGenerator();
        WalletFinder finder = new WalletFinder(repository);
        handler = new FindWalletQueryHandler(finder);
    }


    @Test
    public void find_wallet(){
        WalletId id = new WalletId(generator.generate());
        WalletBalance current_balance = new WalletBalance(new BigDecimal(""+Math.random()));
        when(repository.find(id))
                .thenReturn(
                        Optional.of(new Wallet(id, current_balance))
                );

        FindWalletQuery query = new FindWalletQuery(id.value());

        WalletResponse response = new WalletResponse(id.value(), current_balance.value().toString());

        assertEquals(response, handler.handle(query));
    }
    @Test
    public void not_find_a_non_existing_wallet(){
        WalletId id = new WalletId(generator.generate());
        when(repository.find(id))
                .thenReturn(
                        Optional.empty()
                );

        FindWalletQuery query = new FindWalletQuery(id.value());

        assertThrows(WalletNotExistError.class, () -> handler.handle(query));
    }
}
