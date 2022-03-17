
package com.unobank.servicehub.platform.commonlib.dto.error.bayad;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class BayadError {

    private String timestamp;
    private Details details;
    private String exception;
    private String message;
    private Long status;

    public BayadError(String timestamp, Details details, String exception, String message, Long status) {
        this.timestamp = timestamp;
        this.details = details;
        this.exception = exception;
        this.message = message;
        this.status = status;
    }
}
