package com.unobank.servicehub.platform.commonlib.dto.error;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** @author ankur.goel */
@Data
@NoArgsConstructor
public class ErrorResponse implements Serializable {

  private String timestamp;
  private List<Error> errors;

  public void addError(Error error) {
    if (CollectionUtils.isEmpty(this.errors)) {
      this.errors = new ArrayList<>();
    }
    errors.add(error);
  }
}
