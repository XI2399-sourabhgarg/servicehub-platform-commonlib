
package com.unobank.servicehub.platform.commonlib.dto.error.bayad;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Details {

    private Long code;
    private String message;

}
