package com.unobank.servicehub.platform.commonlib.exception;

import org.springframework.http.HttpStatus;

public class PaynamicsException extends RuntimeException {

    private HttpStatus status;

    public PaynamicsException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
