package stab.build.utils;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

/**
 * Util class for date.
 */
@Log4j
public class DateUtil {

    private static final String DAYS = "yyyyMMdd";
    private static final String SECONDS = "yyyyMMddHHmmss";
    private static final String HAM_DAYS = "yyyy-MM-dd";
    private static final String READABLE_DAYS = "dd-MMM-yy";

    private static final FastDateFormat LONG_FORMAT_UTC = getFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private static final FastDateFormat SECONDS_FORMAT = getFormat(SECONDS);
    private static final FastDateFormat DAYS_FORMAT = getFormat(DAYS);
    private static final FastDateFormat HAM_DAYS_FORMAT = getFormat(HAM_DAYS);
    private static final FastDateFormat READABLE_DAYS_FORMAT = getFormat(READABLE_DAYS);
    private static final String DEFAULT_TIME_ZONE = "UTC";

    public static synchronized Date getDate(String date) {
        Date result = null;

        try {
            if (date.length() == SECONDS.length()) {
                result = SECONDS_FORMAT.parse(date);
            } else if (date.length() == DAYS.length()) {
                result = DAYS_FORMAT.parse(date);
            } else if (date.length() == HAM_DAYS.length()) {
                result = HAM_DAYS_FORMAT.parse(date);
            } else if (date.length() == READABLE_DAYS.length()) {
                result = READABLE_DAYS_FORMAT.parse(date);
            } else {
                result = LONG_FORMAT_UTC.parse(date);
            }
        } catch (ParseException e) {
            log.error("Error in parsing date: " + date);
        }

        return result;
    }

    private static FastDateFormat getFormat(String pattern) {
        return getFormatForTimezone(pattern, DEFAULT_TIME_ZONE);
    }

    private static FastDateFormat getFormatForTimezone(String pattern, String timezone) {
        return FastDateFormat.getInstance(pattern, TimeZone.getTimeZone(timezone));
    }
}
