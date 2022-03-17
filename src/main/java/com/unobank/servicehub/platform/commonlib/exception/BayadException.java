package com.unobank.servicehub.platform.commonlib.exception;

import org.springframework.http.HttpStatus;

/** @author ankur.goel */
public class BayadException extends RuntimeException {

  private HttpStatus status;

  public BayadException(HttpStatus status, String message) {
    super(message);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
