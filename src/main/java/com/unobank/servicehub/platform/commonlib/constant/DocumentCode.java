package com.unobank.servicehub.platform.commonlib.constant;

import com.unobank.servicehub.platform.commonlib.dto.enums.DocumentType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shreyas kekre
 */
public class DocumentCode {

    private static Map<String, String> documentCodeMap = new HashMap<>();

    static {
        documentCodeMap.put(DocumentType.CIVIL_REG_NUMBER.getValue(), com.unobank.servicehub.platform.commonlib.dto.enums.DocumentCode.CIVIL_REG_NUMBER.getValue());
        documentCodeMap.put(DocumentType.LEGAL_CERTIF.getValue(), com.unobank.servicehub.platform.commonlib.dto.enums.DocumentCode.LEGAL_CERTIF.getValue());
        documentCodeMap.put(DocumentType.PERSONAL_CARD.getValue(), com.unobank.servicehub.platform.commonlib.dto.enums.DocumentCode.PERSONAL_CARD.getValue());
        documentCodeMap.put(DocumentType.RESIDENCE_CARD.getValue(), com.unobank.servicehub.platform.commonlib.dto.enums.DocumentCode.RESIDENCE_CARD.getValue());
        documentCodeMap.put(DocumentType.UNIQUE_ID_NUMBER.getValue(), com.unobank.servicehub.platform.commonlib.dto.enums.DocumentCode.UNIQUE_ID_NUMBER.getValue());
        documentCodeMap.put(DocumentType.TAX_ID_NUMBER.getValue(), com.unobank.servicehub.platform.commonlib.dto.enums.DocumentCode.TAX_ID_NUMBER.getValue());
        documentCodeMap.put(DocumentType.VAT_REGISTRATION.getValue(), com.unobank.servicehub.platform.commonlib.dto.enums.DocumentCode.VAT_REGISTRATION.getValue());
        documentCodeMap.put(DocumentType.PASSPORT.getValue(), com.unobank.servicehub.platform.commonlib.dto.enums.DocumentCode.PASSPORT.getValue());
        documentCodeMap.put(DocumentType.DRIVERS_LICENSE.getValue(), com.unobank.servicehub.platform.commonlib.dto.enums.DocumentCode.DRIVERS_LICENSE.getValue());
        documentCodeMap.put(DocumentType.PERSONAL_ID_NUM.getValue(), com.unobank.servicehub.platform.commonlib.dto.enums.DocumentCode.PERSONAL_ID_NUM.getValue());
    }

    public static String getDocumentCode(String documentType) {
        return documentCodeMap.get(documentType);

    }
}
