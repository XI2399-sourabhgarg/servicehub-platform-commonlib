package com.unobank.servicehub.platform.commonlib.dto.enums;

/**
 * @author shreyas kekre
 */
public enum DocumentType {

    CIVIL_REG_NUMBER("CIVIL REGISTER NUMBER"),
    LEGAL_CERTIF("LEGAL CERTIFICATE"),
    PERSONAL_CARD("PERSONAL CARD"),
    RESIDENCE_CARD("RESIDENCE CARD"),
    UNIQUE_ID_NUMBER("UNIQUE ID NUMBER"),
    TAX_ID_NUMBER("TAX ID NUMBER"),
    VAT_REGISTRATION("VAT REGISTRATION"),
    PASSPORT("PASSPORT"),
    DRIVERS_LICENSE("DRIVERS LICENSE"),
    PERSONAL_ID_NUM("PERSONAL ID NUM");

    String key;

    DocumentType(String key) {
        this.key = key;
    }

    DocumentType() {
    }

    public String getValue() {
        return this.key;
    }
}
