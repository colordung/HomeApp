package com.colordung.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class DateUtil {
    public static Date nowToDate() {
        return Date.from(LocalDateTime.now().toInstant(ZoneOffset.ofHours(9)));
    }

    public static Date nowAfterDaysToDate(Long days) {
        return Date.from(LocalDateTime.now().plusDays(days).toInstant(ZoneOffset.ofHours(9)));
    }
    
    public static Date nowAfterDaysToMinutes(Long minutes) {
        return Date.from(LocalDateTime.now().plusMinutes(minutes).toInstant(ZoneOffset.ofHours(9)));
    }

    public static String datetimeToString(Date date) {
        return new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(date);
    }

    public static String datetimeToStrigPattern(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

}
