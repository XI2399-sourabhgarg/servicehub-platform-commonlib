package com.unobank.servicehub.platform.commonlib.dto.enums;

/**
 * @author shreyas kekre
 */
public enum TitleCode {

    MONSIEUR("01"),
    MADAME("02"),
    MADEMOISELLE("03");

    String key;

    TitleCode(String key) {
        this.key = key;
    }

    TitleCode() {
    }

    public String getValue() {
        return this.key;
    }
}
