package com.unobank.servicehub.platform.commonlib.dto.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author shreyas kekre
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrankasError implements Serializable {

    private String timestamp;
    private String code;
    private String message;
}
