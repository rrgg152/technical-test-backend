package com.playtomic.tests.wallet.shared.domain;

import java.util.UUID;
@DomainService
public interface UuidGenerator {
    default String generate(){
        return UUID.randomUUID().toString();
    }
}