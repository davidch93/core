package com.dch.core.common.util;

import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Classes that provides function to manipulate Date.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public class DateUtil {

    /**
     * Format the given date into string.
     *
     * @param date   the {@link Date input date}.
     * @param format the format date in string ("dd-MM-yyyy" or "HH:mm" or others).
     * @return {@link String} date in string.
     */
    public static String formatDate(Date date, String format) {
        return formatDate(date, format, TimeZone.getDefault());
    }

    /**
     * Format the given date into string.
     *
     * @param date     the {@link Date input date}.
     * @param format   the format date in string ("dd-MM-yyyy" or "HH:mm" or others).
     * @param timeZone the {@link TimeZone}.
     * @return {@link String} date in string.
     */
    public static String formatDate(Date date, String format, TimeZone timeZone) {
        Assert.notNull(date, "Parameter date can't be null");
        Assert.hasLength(format, "Parameter format can't be empty");
        Assert.notNull(timeZone, "Parameter time zone can't be null");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(date);
    }

    /**
     * Parse the given string date into date.
     *
     * @param dateString the string date.
     * @param format     the format date in string ("dd-MM-yyyy" or "HH:mm" or others).
     * @return the {@link Date parsed date}.
     */
    public static Date parseDate(String dateString, String format) {
        return parseDate(dateString, format, TimeZone.getDefault());
    }

    /**
     * Parse the given string date into date.
     *
     * @param dateString the string date.
     * @param format     the format date in string ("dd-MM-yyyy" or "HH:mm" or others).
     * @param timeZone   the {@link TimeZone}.
     * @return the {@link Date parsed date}.
     */
    public static Date parseDate(String dateString, String format, TimeZone timeZone) {
        Assert.hasLength(dateString, "Parameter date can't be empty");
        Assert.hasLength(format, "Parameter format can't be empty");
        Assert.notNull(timeZone, "Parameter time zone can't be null");

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            simpleDateFormat.setTimeZone(timeZone);
            return simpleDateFormat.parse(dateString);
        } catch (ParseException ex) {
            throw new RuntimeException("Specified string can't be parsed!", ex);
        }
    }

    /**
     * Set beginning of day. Truncate time detail Sat Apr 22 19:22:23 WIB 2017 to Sat Apr 22 00:00:00 WIB 2017.
     *
     * @param date the {@link Date input date}.
     * @return the beginning of {@link Date} Eg. Sat Apr 22 00:00:00 WIB 2017
     */
    public static Date setBeginningOfDay(Date date) {
        return setBeginningOfDay(date, TimeZone.getDefault());
    }

    /**
     * Set beginning of day. Truncate time detail Sat Apr 22 19:22:23 WIB 2017 to Sat Apr 22 00:00:00 WIB 2017.
     *
     * @param date     the {@link Date input date}
     * @param timeZone the {@link TimeZone}.
     * @return the beginning of {@link Date} Eg. Sat Apr 22 00:00:00 WIB 2017
     */
    public static Date setBeginningOfDay(Date date, TimeZone timeZone) {
        Assert.notNull(date, "Parameter date can't be null");
        Assert.notNull(timeZone, "Parameter time zone can't be null");

        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Set ending of day. Truncate time detail Sat Apr 22 19:22:23 WIB 2017 to Sat Apr 22 23:59:59 WIB 2017.
     *
     * @param date the {@link Date input date}.
     * @return the ending of {@link Date} Eg. Sat Apr 22 23:59:59 WIB 2017
     */
    public static Date setEndingOfDay(Date date) {
        return setEndingOfDay(date, TimeZone.getDefault());
    }

    /**
     * Set ending of day. Truncate time detail Sat Apr 22 19:22:23 WIB 2017 to Sat Apr 22 23:59:59 WIB 2017.
     *
     * @param date     the {@link Date input date}
     * @param timeZone the {@link TimeZone}.
     * @return the ending of {@link Date} Eg. Sat Apr 22 23:59:59 WIB 2017
     */
    public static Date setEndingOfDay(Date date, TimeZone timeZone) {
        Assert.notNull(date, "Parameter date can't be null");
        Assert.notNull(timeZone, "Parameter time zone can't be null");

        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * Add the given date with int value.
     *
     * @param date      the {@link Date input date}.
     * @param numOfDays the number of days.
     * @return the {@link Date} after added number of days.
     */
    public static Date addDate(Date date, int numOfDays) {
        return addDate(date, numOfDays, TimeZone.getDefault());
    }

    /**
     * Add the given date with int value.
     *
     * @param date      the {@link Date input date}
     * @param numOfDays the number of days.
     * @param timeZone  the {@link TimeZone}.
     * @return the {@link Date} after added number of days.
     */
    public static Date addDate(Date date, int numOfDays, TimeZone timeZone) {
        Assert.notNull(date, "Parameter date can't be null");
        Assert.notNull(timeZone, "Parameter time zone can't be null");

        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(date);
        calendar.add(Calendar.DATE, numOfDays);
        return calendar.getTime();
    }

    /**
     * Add the given hour with int value.
     *
     * @param date       the {@link Date input date}
     * @param numOfHours the number of hours.
     * @return the {@link Date} after added number of hours.
     */
    public static Date addHour(Date date, int numOfHours) {
        return addHour(date, numOfHours, TimeZone.getDefault());
    }

    /**
     * Add the given hour with int value.
     *
     * @param date       the {@link Date input date}
     * @param numOfHours the number of hours.
     * @param timeZone   the {@link TimeZone}.
     * @return the {@link Date} after added number of hours.
     */
    public static Date addHour(Date date, int numOfHours, TimeZone timeZone) {
        Assert.notNull(date, "Parameter date can't be null");
        Assert.notNull(timeZone, "Parameter time zone can't be null");

        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, numOfHours);
        return calendar.getTime();
    }

    /**
     * Add the given minute with int value.
     *
     * @param date         the {@link Date input date}
     * @param numOfMinutes the number of minutes.
     * @return the {@link Date} after added number of minutes.
     */
    public static Date addMinute(Date date, int numOfMinutes) {
        return addMinute(date, numOfMinutes, TimeZone.getDefault());
    }

    /**
     * Add the given minute with int value.
     *
     * @param date         the {@link Date input date}
     * @param numOfMinutes the number of minutes.
     * @param timeZone     the {@link TimeZone}.
     * @return the {@link Date} after added number of minutes.
     */
    public static Date addMinute(Date date, int numOfMinutes, TimeZone timeZone) {
        Assert.notNull(date, "Parameter date can't be null");
        Assert.notNull(timeZone, "Parameter time zone can't be null");

        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, numOfMinutes);
        return calendar.getTime();
    }

    /**
     * Add the given second with int value.
     *
     * @param date         the {@link Date input date}
     * @param numOfSeconds the number of seconds.
     * @return the {@link Date} after added number of seconds.
     */
    public static Date addSecond(Date date, int numOfSeconds) {
        return addSecond(date, numOfSeconds, TimeZone.getDefault());
    }

    /**
     * Add the given second with int value.
     *
     * @param date         the {@link Date input date}
     * @param numOfSeconds the number of seconds.
     * @param timeZone     the {@link TimeZone}.
     * @return the {@link Date} after added number of seconds.
     */
    public static Date addSecond(Date date, int numOfSeconds, TimeZone timeZone) {
        Assert.notNull(date, "Parameter date can't be null");
        Assert.notNull(timeZone, "Parameter time zone can't be null");

        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, numOfSeconds);
        return calendar.getTime();
    }
}
