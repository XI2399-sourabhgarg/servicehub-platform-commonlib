package com.unobank.servicehub.platform.commonlib.constant;

import com.unobank.servicehub.platform.commonlib.dto.enums.Title;

import java.util.HashMap;
import java.util.Map;


/**
 * @author shreyas kekre
 */
public class TitleCode {

    private static Map<String, String> titleCodeMap = new HashMap<>();

    static {
        titleCodeMap.put(Title.MONSIEUR.getValue(), com.unobank.servicehub.platform.commonlib.dto.enums.TitleCode.MONSIEUR.getValue());
        titleCodeMap.put(Title.MADAME.getValue(), com.unobank.servicehub.platform.commonlib.dto.enums.TitleCode.MADAME.getValue());
        titleCodeMap.put(Title.MADEMOISELLE.getValue(), com.unobank.servicehub.platform.commonlib.dto.enums.TitleCode.MADEMOISELLE.getValue());
    }

    public static String getTitleCode(String salutation) {
        return titleCodeMap.get(salutation);
    }
}
