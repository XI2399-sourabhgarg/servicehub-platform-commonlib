package com.unobank.servicehub.platform.commonlib.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author tanay sen
 */
@Slf4j
public class ApplicationUtil {

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        /*System.out.println(uuid);
        System.out.println(uuid.variant());
        System.out.println(uuid.version());*/
        return uuid.toString();
    }

    public static String getUniqueProcessId(int length) {
        return String.valueOf(
                length < 1
                        ? 0
                        : new Random().nextInt((9 * (int) Math.pow(10, length - 1)) - 1)
                        + (int) Math.pow(10, length - 1));
    }

    public static String generateExternalId(String suffix, String transactionID) {
        return "XEB" + "-" + transactionID + "-" + suffix;
    }

    public static String generateOrchestrationId(String suffix, String transactionID) {
        return "ORC" + "-" + transactionID + "-" + suffix;
    }

    public static String getUniqueProcessId() {
        String[] uuidStrings = getUUID().split("-");
        return uuidStrings[1] + Instant.now().toEpochMilli() + uuidStrings[3];
    }

    public static String addDays(String dateString, int days) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = inputFormat.parse(dateString);
        } catch (ParseException e) {
            log.info("date not valid");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return outputFormat.format(cal.getTime());
    }


    public static Double calculateTax(Double amount, double taxPercentage) {
        log.info("calculating taxes on feesAmount  :{}", amount);
        try {
            return amount * taxPercentage / 100;
        } catch (ArithmeticException e) {
            throw new ArithmeticException("Error: While calculating tax");
        }
    }

    public static Double roundOffTo2DecPlaces(Double val) {
        return Double.parseDouble(String.format("%.2f", val));
    }

}
