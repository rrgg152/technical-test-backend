package com.playtomic.tests.wallet.infrastructure.persistence;

import com.playtomic.tests.wallet.domain.Wallet;
import com.playtomic.tests.wallet.domain.WalletBalance;
import com.playtomic.tests.wallet.domain.WalletId;
import com.playtomic.tests.wallet.domain.WalletRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
public class H2WalletRepositoryShould {

    @Autowired
    private WalletRepository repository;

    @Test
    public void save_a_wallet() {
        Wallet wallet = new Wallet(new WalletId("a7f16aa2-158d-11ec-82a8-0242ac130003"), new WalletBalance(new BigDecimal("0.00")));
        repository.save(wallet);
    }
    @Test
    public void find_an_existing_wallet() {
        Wallet wallet = new Wallet(new WalletId("a7f16aa2-158d-11ec-82a8-0242ac130003"), new WalletBalance(new BigDecimal("0.00")));
        repository.save(wallet);
        assertEquals(Optional.of(wallet), repository.find(wallet.id()));
    }
}
