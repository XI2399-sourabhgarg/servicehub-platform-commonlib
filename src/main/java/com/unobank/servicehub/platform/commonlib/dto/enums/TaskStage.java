package com.unobank.servicehub.platform.commonlib.dto.enums;

/**
 * @author tanay sen
 */
public enum TaskStage {
    DEBIT_FAILED("Debit Partner GL Account"),
    CREDIT_FAILED("Credit Payee Account"),
    APPLY_FEE_FAILED("Apply Fee"),
    APPLY_TAX_FAILED("Apply Taxes"),
    APPLY_GL_POSTING_FAILED("Apply GL Posting"),
    MATURITY_DEPOSIT_FAILED("Maturity Deposit"),
    CREATE_DEBIT_CARD_FAILED("Create Debit Card"),
    UPDATE_CARD_ISSUANCE_STATUS_FAILED("Update Debit Card Issuance"),
    SEND_SMS_STATUS_FAILED("Send SMS"),
    APPLY_FEE_ON_DST_FAILED("APPLY FEE ON DST AMOUNT"),
    APPLY_FEE_ON_PENALTY_FAILED("APPLY FEE ON PENALTY AMOUNT"),
    FD_WITHDRAWAL_FAILED("FD ACCOUNT WITHDRAWAL FAILED"),
    CASA_DEPOSIT_FAILED("CASA ACCOUNT DEPOSIT FAILED"),
    GRT_ON_DST_FAILED("GL POSTING FOR GRT ON DST AMOUNT FAILED"),
    GRT_ON_PENALTY_FAILED("GL POSTING FOR GRT ON PENALTY AMOUNT FAILED"),
    FD_ACCOUNT_STATUS_UPDATE_FAILED("FD ACCOUNT STATUS UPDATE FAILED"),
    DFT_APPLY_TAX_FAILED("APPLY TAX FAILED FOR DFT");


    String key;

    TaskStage(String key) {
        this.key = key;
    }

    public static String getValue(TaskMessageCode code) {
        return TaskStage.valueOf(code.name()).key;
    }

}
