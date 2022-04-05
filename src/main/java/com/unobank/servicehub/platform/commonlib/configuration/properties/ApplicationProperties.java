package com.unobank.servicehub.platform.commonlib.configuration.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author shreyas kekre
 */
@Component
@Data
public class ApplicationProperties {

    @Value("${dstCalc.parameters.paramOne}")
    private double paramOne;

    @Value("${dstCalc.parameters.paramTwo}")
    private double paramTwo;

    @Value("${mambu-api.taxapi.percentage}")
    private Double taxPercentage;

    @Value("${mambu-api.taxapi.glDebitAccno}")
    private String debitAccountNo;

    @Value("${mambu-api.taxapi.glCreditAccno}")
    private String creditAccountNo;

    @Value("${mambu-api.taxapi.dstGlDebitAccno}")
    private String debitDstAccountNo;

    @Value("${mambu-api.taxapi.dstGlCreditAccno}")
    private String creditDstAccountNo;

    @Value("${brankas-api.sourceAccountId}")
    private String sourceAccountId;

    @Value("${bayad-api.callbackURL}")
    private String callbackURL;


    @Value("${debitCard.eligibilty.amount}")
    private Double minDebitCardAmount;

    @Value("${mambu-api.create-task.api.assigned-user.key}")
    private String assignedUserKey;

    @Value("${mambu-api.create-task.api.sla.day}")
    private int addDayCount;

}
