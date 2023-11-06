package com.learning.management.utils;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    public static ZonedDateTime strDateToZonedDateTime(String strDate) {
        final DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneOffset.UTC);

        return ZonedDateTime.parse(strDate, formatter);
    }
}
