package com.playtomic.tests.wallet.api;

import com.playtomic.tests.wallet.domain.error.InsufficientAmountError;
import com.playtomic.tests.wallet.domain.error.InvalidUUIDFormatError;
import com.playtomic.tests.wallet.domain.error.WalletNotExistError;
import com.playtomic.tests.wallet.shared.domain.DomainError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ErrorExceptionHandler
  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
      = { DomainError.class })
    protected ResponseEntity<Object> handle(
            DomainError domainError, WebRequest request) {
        return handleExceptionInternal(domainError, domainError.errorMessage(),
          new HttpHeaders(), errorMapping().getOrDefault(domainError.getClass(), HttpStatus.INTERNAL_SERVER_ERROR), request);
    }

    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<>() {{
            put(WalletNotExistError.class, HttpStatus.NOT_FOUND);
            put(InsufficientAmountError.class, HttpStatus.BAD_REQUEST);
            put(InvalidUUIDFormatError.class, HttpStatus.BAD_REQUEST);
        }};
    }
}