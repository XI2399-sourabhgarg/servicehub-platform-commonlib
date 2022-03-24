package com.unobank.servicehub.platform.commonlib.dto.enums;

/**
 * @author tanay sen
 */

public enum FieldEnum {
    ENCODED_KEY("encodedKey"),
    CREDIT_OFFICER_KEY("creditOfficerKey"),
    CLIENT_ROLE_KEY("clientRoleKey"),
    BRANCH_KEY("branchKey"),
    CENTRE_KEY("centreKey"),
    GROUP_KEY("groupKey"),
    FULL_NAME("fullName"),
    FIRST_NAME("firstName"),
    MIDDLE_NAME("middleName"),
    LAST_NAME("lastName"),
    CREATION_DATE("creationDate"),
    LAST_MODIFIED_DATE("lastModifiedDate"),
    ID("id"),
    DEPOSITS_BALANCE("depositsBalance"),
    LOANS_BALANCE("loansBalance"),
    PENDING_LOAN_AMOUNT("pendingLoanAmount"),
    APPROVED_LOAN_AMOUNT("approvedLoanAmount"),
    TOTAL_BALANCE("totalBalance"),
    TOTAL_DUE("totalDue"),
    HOME_PHONE_NUMBER("homePhoneNumber"),
    MOBILE_PHONE_NUMBER("mobilePhoneNumber"),
    MOBILE_PHONE_NUMBER_2("mobilePhoneNumber2"),
    EMAIL_ADDRESS("emailAddress"),
    CLIENT_ADDRESS("clientAddress"),
    BIRTH_DATE("birthdate"),
    GENDER("gender"),
    LOAN_CYCLE("loanCycle"),
    GROUP_LOAN_CYCLE("groupLoanCycle"),
    CLIENT_STATE("clientState"),
    PORTAL_STATE("portalState"),
    PREFERRED_LANGUAGE("preferredLanguage"),
    GROUP_ID("groupId"),
    NAME("name"),
    ACCOUNT_HOLDER_KEY("accountHolderKey"),
    CLIENT_ID("clientId"),
    ACCOUNT_HOLDER_NAME("accountHolderName"),
    ACCOUNT_STATE("accountState"),
    ACCOUNT_TYPE("accountType"),
    ACTIVATION_DATE("activationDate"),
    APPROVED_DATE("approvedDate"),
    MATURITY_DATE("maturityDate"),
    LAST_SET_TO_ARREARS_DATE("lastSetToArrearsDate"),
    CLOSED_DATE("closedDate"),
    ASSIGNED_BRANCH_KEY("assignedBranchKey"),
    ASSIGNED_CENTRE_KEY("assignedCentreKey"),
    ASSIGNED_USER_KEY("assignedUserKey"),
    CURRENCY_CODE("currencyCode"),
    LENGTH_IN_DAYS("lengthInDays"),
    OVERDRAFT_RISK_LEVEL_KEY("overdraftRiskLevelKey"),
    OVERDRAFT_AVAILABLE_LIMIT("overdraftAvailableLimit"),
    OVERDRAFT_DAYS_IN_ARREARS("overdraftDaysInArrears"),
    OVERDRAFT_IN_ARREARS("overdraftInArrears"),
    PRODUCT_TYPE_KEY("productTypeKey"),
    PRODUCT_CATEGORY("productCategory"),
    TAX_APPLIED("taxApplied"),
    WITHHOLDING_TAX_SOURCE_KEY("withholdingTaxSourceKey"),
    TAX_RATE("taxRate"),
    TYPE("type"),
    PARENT_ACCOUNT_ID("parentAccountID"),
    VALUE_DATE("valueDate");

    String key;

    FieldEnum(String key) {
        this.key = key;
    }

    FieldEnum() {
    }

    public String getValue() {
        return this.key;
    }
}
