package com.unobank.servicehub.platform.commonlib.event;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class BundleProcessEvent implements Serializable {

    private boolean isAccountStateChangeFailed;
    private boolean isAccountCreationFailed;
    private String clientId;
    private String accountId;
    private String processId;

}