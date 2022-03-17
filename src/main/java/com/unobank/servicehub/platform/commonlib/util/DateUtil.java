package com.unobank.servicehub.platform.commonlib.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author ankur.goel
 */
@Slf4j
public class DateUtil {

    private static final DateTimeFormatter defaultFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    private DateUtil() {
    }

    public static String convertDateToCustomFormatString(LocalDateTime date) {
        return date.format(defaultFormatter);
    }

    public static String currentDateAccToSingapore() {

        Date date = new Date(System.currentTimeMillis() - 60 * 1000);
        OffsetDateTime odtB = OffsetDateTime.parse(sdf.format(date));
        ZoneOffset myOffset = ZoneOffset.ofHours(8);
        odtB = odtB.withOffsetSameInstant(myOffset);
        log.info("date format:" + sdf.format(date));
        String output = odtB.toString();
        return output;
    }

    public static long getTimeMillis(String dateString) {
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * Get current date in "yyyy-MM-dd'T'HH:mm:ss" format.
     * @return
     */
    public static String getCurrentDate() {
        Date date = new Date(System.currentTimeMillis());
        return  simpleDateFormat.format(date);
    }
}
