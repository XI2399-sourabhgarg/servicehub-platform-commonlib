package com.unobank.servicehub.platform.commonlib.exception;

import lombok.Data;

@Data
public class InvalidHeaderFieldException extends RuntimeException {

  private String message;

  public InvalidHeaderFieldException(String message) {
    this.message = message;
  }
}
