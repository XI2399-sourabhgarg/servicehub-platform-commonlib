package com.unobank.servicehub.platform.commonlib.dto.enums;

/**
 * @author shreyas kekre
 */
public enum Title {

    MONSIEUR("M."),
    MADAME("Mme"),
    MADEMOISELLE("Mlle");

    String key;

    Title(String key) {
        this.key = key;
    }

    Title() {
    }

    public String getValue() {
        return this.key;
    }
}
