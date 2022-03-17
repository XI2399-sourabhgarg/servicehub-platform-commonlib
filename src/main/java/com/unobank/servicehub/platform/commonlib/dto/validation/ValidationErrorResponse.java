package com.unobank.servicehub.platform.commonlib.dto.validation;

import lombok.Data;

import java.util.Map;

@Data
public class ValidationErrorResponse {

  private String timestamp;
  private String message;
  private Map<String, String> errors;

  public ValidationErrorResponse() {}
}
