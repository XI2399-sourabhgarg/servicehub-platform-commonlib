package com.unobank.servicehub.platform.commonlib.util;

/**
 * @author ankur.goel
 */
public final class ApiPaths {

    public static final String BASE_PATH_V1 = "v1/api/custom";
    public static final String ONBOARDING = "customer";
    public static final String FUNDTRANSFER = "payment";
    public static final String DEPOSIT = "deposit";
    public static final String ACCOUNT = "account";

    public static final class AuthEntity {
        public static final String BP_AUTH = "v1/auth";
        public static final String BP_AUTH_V1 = BASE_PATH_V1 + "/auth";
    }

    public static final class OnBoardingEntity {
        public static final String BP_ONBOARDING = ONBOARDING + "/v1/onBoarding";
        public static final String BP_ONBOARD_CLIENT = BP_ONBOARDING + "/client";
        public static final String BP_ONBOARD_PRODUCT = BP_ONBOARDING + "/products";
        public static final String BP_ONBOARD_ACCOUNT = BP_ONBOARDING + "/account";
        public static final String BP_ONBOARDING_V1 = BASE_PATH_V1 + "/onBoarding";
    }

    public static final class ClientEntity {
        public static final String CLIENT = "/v1/client";
    }

    public static final class CardEntity {
        public static final String card = "/v1/card/initiateProcess";
    }

    public static final class AccountSearch {
        public static final String TF_ACCT_SEARCH = ACCOUNT + "/accountSearch";
    }

    public static final class DepositAccountEntity {
        public static final String ODA_OPEN_DEPOSIT = ACCOUNT + "/v1/deposit";
        public static final String MAUTRITY_DEPOSTI_ACCOUNT = ACCOUNT + "/maturityDepositAccount";
        public static final String RDA_REDEEM_DEPOSIT = DEPOSIT + "/v1/fd-preClosure";
        public static final String ODA_OPEN_DEPOSIT_V1 = BASE_PATH_V1 + "/openDeposit";
    }

    public static final class InfoBipEntity {

    }

    public static final class DebitCardEntity {
        public static final String CREATE_DEBIT_CARD =  "/createDebitCard";
        public static final String SEND_SMS =  "sms/sendSmsMessage";
        public static final String DEBIT_CARD =  "/issue/debitCard";
    }

    public static final class FundTransferEntity {
        public static final String FT_APPLY_FEE = FUNDTRANSFER + "/{accountId}/applyFee";
        public static final String FT_APPLY_TAXES = FUNDTRANSFER + "/applyTaxes";
        public static final String BP_SELF_FUNDTRANSFER = FUNDTRANSFER + "/sft/v1/payOutInitiate";
        public static final String BP_DOMESTIC_FUNDTRANSFER = FUNDTRANSFER + "/dft/v1/payOutInitiate";
        public static final String FT_BRANKAS_DISBURSEMENTS = FUNDTRANSFER + "/brankas/disbursements";
        public static final String BP_OTC_FUNDTRANSFER = FUNDTRANSFER + "/dft/otc/v1/payInPosting";
        public static final String FT_TRANSACTION = FUNDTRANSFER + "/transaction";
        public static final String FT_TASK = FUNDTRANSFER + "/task";
        public static final String FT_APPLY_INTEREST = ACCOUNT + "/{accountId}/applyInterest";
        public static final String BP_BILLPAYMENT_FUNDTRANSFER = FUNDTRANSFER + "/bill/v1/payOutInitiate";
        public static final String BP_SELF_FUNDTRANSFER_V1 = BASE_PATH_V1 + "/sft/payOutInitiate";
        public static final String BP_DOMESTIC_FUNDTRANSFER_V1 = BASE_PATH_V1 + "/dft/payOutInitiate";
        public static final String BP_OTC_FUNDTRANSFER_V1 = BASE_PATH_V1 + "/dft/payinPosting";
        public static final String BP_OTC_CASH_OUT = FUNDTRANSFER + "/dft/otc/v1/payOutPosting";
    }
}
