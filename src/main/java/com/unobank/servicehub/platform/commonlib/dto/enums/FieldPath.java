package com.unobank.servicehub.platform.commonlib.dto.enums;

/**
 * @author tanay sen
 */
public enum FieldPath {

    CARD_ISSUANCE_STATUS("/_Personal_Details/card_Issuance_Status"),
    FIRST_NAME("/firstName"),
    LAST_NAME("/lastName"),
    NUMBER_OF_DEPENDENTS("/_Personal_Details/no_Of_Dependents"),
    PAYOUT_TRANSACTION_ID("/_trans_Payout_Info/payout_Trans_Id"),
    PAYOUT_TRANSACTION_STATUS("/_trans_Payout_Info/trans_Payout_Status"),
    PAYOUT_TRANSACTION_DONE_ON("/_trans_Payout_Info/payout_Done_On");

    String key;

    FieldPath() {
    }

    FieldPath(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.key;
    }
}
