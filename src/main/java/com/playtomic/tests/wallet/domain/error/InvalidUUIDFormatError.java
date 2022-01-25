package com.playtomic.tests.wallet.domain.error;

import com.playtomic.tests.wallet.shared.domain.DomainError;

public class InvalidUUIDFormatError extends DomainError {
    public InvalidUUIDFormatError(String message) {
        super("wrong_format", message);
    }
}
