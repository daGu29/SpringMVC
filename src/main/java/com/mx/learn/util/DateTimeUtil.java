package com.mx.learn.util;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * DateTimeUtil
 *
 * @author dagu29
 * @date 2020/2/17 0017
 */
public class DateTimeUtil {

    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    //joda-time
    //str - date
    public static Date str2Date(String dateTimeStr, String formatStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    //date - str
    public static String date2Str(Date date, String formatStr) {
        if (date != null) {
            DateTime dateTime = new DateTime(date);
            return dateTime.toString(formatStr);
        }
        return StringUtils.EMPTY;
    }

    //str - date standard
    public static Date str2Date(String dateTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    //date - str standard
    public static String date2Str(Date date) {
        if (date != null) {
            DateTime dateTime = new DateTime(date);
            return dateTime.toString(STANDARD_FORMAT);
        }
        return StringUtils.EMPTY;
    }
}
