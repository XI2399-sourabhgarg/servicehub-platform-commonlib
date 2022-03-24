package com.unobank.servicehub.platform.commonlib.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.unobank.servicehub.platform.commonlib.dto.enums.OperatorEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilterCriteria implements Serializable {

  private String field;
  private OperatorEnum operator;
  private String secondValue;
  private String value;
  private List<String> values;

  public static class Builder {
    private String field;
    private OperatorEnum operator;
    private String value;

    public Builder withField(String field) {
      this.field = field;
      return this;
    }

    public Builder withOperator(OperatorEnum operator) {
      this.operator = operator;
      return this;
    }

    public Builder withValue(String value) {
      this.value = value;
      return this;
    }

    public FilterCriteria build() {
      FilterCriteria filterCriteria = new FilterCriteria();
      filterCriteria.field = field;
      filterCriteria.operator = operator;
      filterCriteria.value = value;
      return filterCriteria;
    }
  }
}
