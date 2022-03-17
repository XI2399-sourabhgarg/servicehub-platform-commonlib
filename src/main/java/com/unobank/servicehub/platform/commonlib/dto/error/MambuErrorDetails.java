package com.unobank.servicehub.platform.commonlib.dto.error;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/** @author ankur.goel */
@Data
public class MambuErrorDetails implements Serializable {

  private Date timestamp;
  private String errorCode;
  private String errorReason;
  private String errorDesc;

  public MambuErrorDetails() {}

  public MambuErrorDetails(Date timestamp, String errorCode, String errorReason, String errorDesc) {
    super();
    this.timestamp = timestamp;
    this.errorCode = errorCode;
    this.errorReason = errorReason;
    this.errorDesc = errorDesc;
  }
}
