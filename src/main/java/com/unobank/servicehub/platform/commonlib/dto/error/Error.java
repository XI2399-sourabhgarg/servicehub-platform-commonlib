package com.unobank.servicehub.platform.commonlib.dto.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** @author ankur.goel */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error implements Serializable {

  private Long errorCode;
  private String errorReason;
  private String errorSource;

  public Error(Long errorCode, String errorReason, String errorSource) {
    this.errorCode = errorCode;
    this.errorReason = errorReason;
    this.errorSource = errorSource;
  }
}
