package com.slab.utils;

import org.apache.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    /**
     * Get future date in specific format
     *
     * @param format date format
     * @param delay future days
     * @return date
     */
    public String getDate(String format, int delay) {
        logger.info("Getting date - format " + format + ", delay days - " + delay);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        long tsDelay = 1000 * 60 * 60 * 24 * delay;
        Timestamp ts = new Timestamp(System.currentTimeMillis() + tsDelay);

        return simpleDateFormat.format(new Date(ts.getTime()));
    }

    /**
     * Get date format for filter search
     *
     * @param delay1 delay for checkin date
     * @param delay2 delay for checkout date
     * @param separator - or –
     * @return date
     */
    public String getFilterDate(int delay1, int delay2, String separator) {
        logger.info("Getting date formatted for search filter");
        String checkInMonth = getDate("MMM", delay1);
        String checkInDay = getDate("dd", delay1);
        if(checkInDay.startsWith("0")) {
            checkInDay = checkInDay.substring(1,2);
        }
        String checkOutMonth = getDate("MMM", delay2);
        String checkOutDay = getDate("dd", delay2);
        if(checkOutDay.startsWith("0")) {
            checkOutDay = checkOutDay.substring(1,2);
        }
        return  (checkInMonth.equalsIgnoreCase(checkOutMonth)) ? checkInMonth + " " + checkInDay + " " + separator + " " + checkOutDay :
                checkInMonth + " " + checkInDay + " " + separator + " " + checkOutMonth + " " + checkOutDay;
    }

}
