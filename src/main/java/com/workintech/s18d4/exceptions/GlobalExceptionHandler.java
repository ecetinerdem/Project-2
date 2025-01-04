package com.workintech.s18d4.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<AccountErrorResponse> handleException(AccountException accountException) {
        log.error("An Account error has occurred ", accountException);
        AccountErrorResponse accountErrorResponse = new AccountErrorResponse(accountException.getHttpStatus().value(),accountException.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(accountErrorResponse, accountException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<AddressErrorResponse> handleException(AddressException addressException) {
        log.error("An Account error has occurred ", addressException);
        AddressErrorResponse addressErrorResponse = new AddressErrorResponse(addressException.getHttpStatus().value(),addressException.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(addressErrorResponse, addressException.getHttpStatus());
    }
}
