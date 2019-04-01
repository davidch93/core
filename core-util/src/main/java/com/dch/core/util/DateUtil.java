package com.dch.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Classes that provides function to manipulate Date.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Apr 22, 2017
 * @since 1.0.0-SNAPSHOT
 */
public class DateUtil {

    /**
     * Method used to format date into string.
     *
     * @param date   {@link Date} date.
     * @param format {@link String} date in format string ("dd-MM-yyyy" or "HH :
     *               mm" or others).
     * @return {@link String} date in string.
     * @throws Exception Parameter date can't be null or Parameter format can't be
     *                   null.
     */
    public static String formatDate(Date date, String format) throws Exception {
        if (date == null)
            throw new Exception("Parameter date can't be null");

        if (!TextUtil.isExist(format))
            throw new Exception("Parameter format can't be null");

        return (new SimpleDateFormat(format).format(date));
    }

    /**
     * Method used to parse date in string into date.
     *
     * @param dateString {@link String} date in string.
     * @param format     {@link String} date in format string ("dd-MM-yyyy" or "HH :
     *                   mm" or others).
     * @return {@link Date} date.
     * @throws Exception Parameter date can't be null or Parameter format can't be
     *                   null.
     */
    public static Date parseDate(String dateString, String format) throws Exception {
        if (!TextUtil.isExist(dateString))
            throw new Exception("Parameter date can't be null");

        if (!TextUtil.isExist(format))
            throw new Exception("Parameter format can't be null");

        return (new SimpleDateFormat(format)).parse(dateString);
    }

    /**
     * Method used to set beginning of day. Method to truncate time detail Sat
     * Apr 22 19:22:23 WIB 2017 to Sat Apr 22 00:00:00 WIB 2017.
     *
     * @param date {@link Date}
     * @return {@link Date} Sat Apr 22 00:00:00 WIB 2017
     * @throws Exception Date can't be null
     */
    public static Date setBeginningOfDay(Date date) throws Exception {
        if (date == null)
            throw new Exception("Date can't be null");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Method used to set ending of day. Method to truncate time detail Sat Apr
     * 22 19:22:23 WIB 2017 to Sat Apr 22 23:59:59 WIB 2017.
     *
     * @param date {@link Date}
     * @return {@link Date} Sat Apr 22 23:59:59 WIB 2017
     * @throws Exception Parameter can't be null
     */
    public static Date setEndingOfDay(Date date) throws Exception {
        if (date == null)
            throw new Exception("Date can't be null");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * Method used to add Date with int value.
     *
     * @param date      {@link Date}
     * @param numOfDays {@link int} number of days.
     * @return {@link Date} date after added number of days.
     * @throws Exception Date can't be null.
     */
    public static Date addDate(Date date, int numOfDays) throws Exception {
        if (date == null)
            throw new Exception("Date can't be null");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, numOfDays);
        return calendar.getTime();
    }

    /**
     * Method used to add Hour with int value.
     *
     * @param date       {@link Date}
     * @param numOfHours {@link int} number of hours.
     * @return {@link Date} date after added number of hours.
     * @throws Exception Date can't be null.
     */
    public static Date addHour(Date date, int numOfHours) throws Exception {
        if (date == null)
            throw new Exception("Date can't be null");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, numOfHours);
        return calendar.getTime();
    }

    /**
     * Method used to add Minutes with int value.
     *
     * @param date         {@link Date}
     * @param numOfMinutes {@link int} number of minutes.
     * @return {@link Date} date after added number of minutes.
     * @throws Exception Date can't be null.
     */
    public static Date addMinute(Date date, int numOfMinutes) throws Exception {
        if (date == null)
            throw new Exception("Date can't be null");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, numOfMinutes);
        return calendar.getTime();
    }

    /**
     * Method used to add Seconds with int value.
     *
     * @param date         {@link Date}
     * @param numOfSeconds {@link int} number of seconds.
     * @return {@link Date} date after added number of seconds.
     * @throws Exception Date can't be null.
     */
    public static Date addSecond(Date date, int numOfSeconds) throws Exception {
        if (date == null)
            throw new Exception("Date can't be null");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, numOfSeconds);
        return calendar.getTime();
    }
}
