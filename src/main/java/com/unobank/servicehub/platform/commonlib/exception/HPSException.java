package com.unobank.servicehub.platform.commonlib.exception;

import org.springframework.http.HttpStatus;

/** @author shreyas.kekre */
public class HPSException extends RuntimeException {

  private HttpStatus status;

  public HPSException(HttpStatus status, String message) {
    super(message);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
