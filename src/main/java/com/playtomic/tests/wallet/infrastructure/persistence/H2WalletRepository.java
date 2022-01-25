package com.playtomic.tests.wallet.infrastructure.persistence;

import com.playtomic.tests.wallet.domain.Wallet;
import com.playtomic.tests.wallet.domain.WalletId;
import com.playtomic.tests.wallet.domain.WalletRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class H2WalletRepository implements WalletRepository {
    private final WalletJPARepository crudRepository;

    public H2WalletRepository(WalletJPARepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Optional<Wallet> find(WalletId id) {
        return crudRepository.findById(id.value());
    }

    @Override
    public void save(Wallet wallet) {
        crudRepository.save(wallet);
    }
}
