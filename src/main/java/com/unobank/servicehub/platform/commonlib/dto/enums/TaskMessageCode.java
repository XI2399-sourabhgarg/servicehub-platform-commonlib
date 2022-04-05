package com.unobank.servicehub.platform.commonlib.dto.enums;

/**
 * @author tanay sen
 */
public enum TaskMessageCode {

    DEBIT_FAILED("debitGL.failed"),
    CREDIT_FAILED("credit.failed"),
    APPLY_FEE_FAILED("applyFee.failed"),
    APPLY_TAX_FAILED("applyTax.failed"),
    MATURITY_DEPOSIT_FAILED("maturityDeposit.failed"),
    APPLY_GL_POSTING_FAILED("applyGLPosting.failed"),
    CREATE_DEBIT_CARD_FAILED("createDebitCard.failed"),
    UPDATE_CARD_ISSUANCE_STATUS_FAILED("updateCardIssuanceStatus.failed"),
    SEND_SMS_STATUS_FAILED("sendSmsStatus.failed"),
    APPLY_FEE_ON_DST_FAILED("dstDebit.failed"),
    APPLY_FEE_ON_PENALTY_FAILED("penaltyDebit.failed"),
    FD_WITHDRAWAL_FAILED("debitAccount.failed"),
    CASA_DEPOSIT_FAILED("creditAccount.failed"),
    GRT_ON_DST_FAILED("debitGRTOnDST.failed"),
    GRT_ON_PENALTY_FAILED("debitGRTOnPenalty.failed"),
    FD_ACCOUNT_STATUS_UPDATE_FAILED("updateAccount.failed"),
    DFT_APPLY_TAX_FAILED("dftApplyTax.failed"),
    DEFAULT("false");

    String key;

    TaskMessageCode() {
    }

    TaskMessageCode(String key) {
        this.key = key;
    }

    public static String getValue(TaskMessageCode operation) {
        return operation.key;
    }

}
