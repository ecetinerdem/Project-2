package com.workintech.s18d4.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerException extends RuntimeException {
    private HttpStatus httpStatus;
    public CustomerException(String message, HttpStatus httpStatus) {
        this.httpStatus =httpStatus;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
