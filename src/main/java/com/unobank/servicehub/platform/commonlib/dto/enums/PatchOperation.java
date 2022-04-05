package com.unobank.servicehub.platform.commonlib.dto.enums;

/**
 * @author tanay sen
 */
public enum PatchOperation {

    REPLACE("replace"),
    ADD("add"),
    REMOVE("remove");
    String key;

    PatchOperation() {
    }

    PatchOperation(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.key;
    }
}
