package com.unobank.servicehub.platform.commonlib.dto.enums;

/**
 * @author shreyas kekre
 */
public enum DocumentCode {

    CIVIL_REG_NUMBER("57"),
    LEGAL_CERTIF("05"),
    PERSONAL_CARD("06"),
    RESIDENCE_CARD("07"),
    UNIQUE_ID_NUMBER("08"),
    TAX_ID_NUMBER("09"),
    VAT_REGISTRATION("10"),
    PASSPORT("01"),
    DRIVERS_LICENSE("02"),
    PERSONAL_ID_NUM("03");

    String key;

    DocumentCode(String key) {
        this.key = key;
    }

    DocumentCode() {
    }

    public String getValue() {
        return this.key;
    }
}
