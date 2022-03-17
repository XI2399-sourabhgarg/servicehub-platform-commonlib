package com.unobank.servicehub.platform.commonlib.dto.enums;

/**
 * @author tanay sen
 */
public enum ExternalID {
    WITHDRAWAL("UW"),
    DEPOSIT("UD"),
    APPLYFEE("UAF"),
    APPLYTAX("UAT");

    String key;

    ExternalID(String key) {
        this.key = key;
    }

    ExternalID() {
    }

    public String getValue() {
        return this.key;
    }
}
