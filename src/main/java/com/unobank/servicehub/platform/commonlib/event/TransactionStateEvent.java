package com.unobank.servicehub.platform.commonlib.event;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionStateEvent {

    private String transactionId;
    private String externalId;
    private String serviceName;
    private String transactionState;
    private String payload;
    private String errorResponse;
    private String timestamp;




}
