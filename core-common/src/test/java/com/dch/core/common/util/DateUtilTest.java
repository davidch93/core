package com.dch.core.common.util;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class used to test all methods in the {@link DateUtil} class.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public class DateUtilTest {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Test method for
     * {@link DateUtil#formatDate(Date, String)}.
     */
    @Test
    public void testFormatDate() {
        String actualDate = DateUtil.formatDate(new Date(), DATE_FORMAT);
        String expectedDate = new SimpleDateFormat(DATE_FORMAT).format(new Date());
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#formatDate(Date, String, TimeZone)}.
     */
    @Test
    public void testFormatDate_withTimeZone() {
        String actualDate = DateUtil.formatDate(new Date(), DATE_FORMAT, TimeZone.getTimeZone("UTC"));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String expectedDate = simpleDateFormat.format(new Date());

        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#formatDate(Date, String)} with empty parameter date.
     */
    @Test
    public void testFormatDate_withEmptyDate_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                DateUtil.formatDate(null, DATE_FORMAT));
        assertThat(ex.getMessage(), equalTo("Parameter date can't be null"));
    }

    /**
     * Test method for
     * {@link DateUtil#formatDate(Date, String)} with empty parameter format.
     */
    @Test
    public void testFormatDate_withEmptyFormat_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                DateUtil.formatDate(new Date(), null));
        assertThat(ex.getMessage(), equalTo("Parameter format can't be empty"));
    }

    /**
     * Test method for
     * {@link DateUtil#formatDate(Date, String, TimeZone)} with null time zone.
     */
    @Test
    public void testFormatDate_withNullTimeZone_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                DateUtil.formatDate(new Date(), DATE_FORMAT, null));
        assertThat(ex.getMessage(), equalTo("Parameter time zone can't be null"));
    }

    /**
     * Test method for {@link DateUtil#formatDate(Date, String)} with invalid string.
     */
    @Test
    public void testParseDate_withInvalidString_thenExpectThrowException() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> DateUtil.parseDate("test", DATE_FORMAT));
        assertThat(ex.getMessage(), equalTo("Specified string can't be parsed!"));
    }

    /**
     * Test method for
     * {@link DateUtil#parseDate(String, String)}.
     */
    @Test
    public void testParseDate() throws Exception {
        String date = "2019-01-01 00:00:00";
        Date actualDate = DateUtil.parseDate(date, DATE_FORMAT);
        Date expectedDate = new SimpleDateFormat(DATE_FORMAT).parse(date);
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#parseDate(String, String, TimeZone)}.
     */
    @Test
    public void testParseDate_withTimeZone() throws Exception {
        String date = "2019-01-01 00:00:00";
        Date actualDate = DateUtil.parseDate(date, DATE_FORMAT, TimeZone.getTimeZone("UTC"));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date expectedDate = simpleDateFormat.parse(date);

        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#parseDate(String, String)} with empty parameter date.
     */
    @Test
    public void testParseDate_withEmptyDate_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                DateUtil.parseDate(null, DATE_FORMAT));
        assertThat(ex.getMessage(), equalTo("Parameter date can't be empty"));
    }

    /**
     * Test method for
     * {@link DateUtil#parseDate(String, String)} with empty parameter format.
     */
    @Test
    public void testParseDate_withEmptyFormat_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                DateUtil.parseDate(new Date().toString(), null));
        assertThat(ex.getMessage(), equalTo("Parameter format can't be empty"));
    }

    /**
     * Test method for
     * {@link DateUtil#parseDate(String, String, TimeZone)} with null time zone.
     */
    @Test
    public void testParseDate_withNullTimeZone_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                DateUtil.parseDate(new Date().toString(), DATE_FORMAT, null));
        assertThat(ex.getMessage(), equalTo("Parameter time zone can't be null"));
    }

    /**
     * Test method for
     * {@link DateUtil#setBeginningOfDay(Date)}.
     */
    @Test
    public void testSetBeginningOfDay() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date actualDate = DateUtil.setBeginningOfDay(date);
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#setBeginningOfDay(Date, TimeZone)}.
     */
    @Test
    public void testSetBeginningOfDay_withTimeZone() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date actualDate = DateUtil.setBeginningOfDay(date, TimeZone.getTimeZone("UTC"));
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#setBeginningOfDay(Date)} with empty parameter date.
     */
    @Test
    public void testSetBeginningOfDay_withEmptyDate_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                DateUtil.setBeginningOfDay(null));
        assertThat(ex.getMessage(), equalTo("Parameter date can't be null"));
    }

    /**
     * Test method for
     * {@link DateUtil#setEndingOfDay(Date)}.
     */
    @Test
    public void testSetEndingOfDay() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        Date actualDate = DateUtil.setEndingOfDay(date);
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#setEndingOfDay(Date, TimeZone)}.
     */
    @Test
    public void testSetEndingOfDay_withTimeZone() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        Date actualDate = DateUtil.setEndingOfDay(date, TimeZone.getTimeZone("UTC"));
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#setEndingOfDay(Date)} with empty parameter date.
     */
    @Test
    public void testSetEndingOfDay_withEmptyDate_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                DateUtil.setEndingOfDay(null));
        assertThat(ex.getMessage(), equalTo("Parameter date can't be null"));
    }

    /**
     * Test method for
     * {@link DateUtil#addDate(Date, int)}.
     */
    @Test
    public void testAddDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);

        Date actualDate = DateUtil.addDate(date, 1);
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#addDate(Date, int, TimeZone)}.
     */
    @Test
    public void testAddDate_withTimeZone() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);

        Date actualDate = DateUtil.addDate(date, 1, TimeZone.getTimeZone("UTC"));
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#addDate(Date, int)} with empty parameter date.
     */
    @Test
    public void testAddDate_withEmptyDate_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                DateUtil.addDate(null, 1));
        assertThat(ex.getMessage(), equalTo("Parameter date can't be null"));
    }

    /**
     * Test method for
     * {@link DateUtil#addHour(Date, int)}.
     */
    @Test
    public void testAddHour() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, 1);

        Date actualDate = DateUtil.addHour(date, 1);
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#addHour(Date, int, TimeZone)}.
     */
    @Test
    public void testAddHour_withTimeZone() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, 1);

        Date actualDate = DateUtil.addHour(date, 1, TimeZone.getTimeZone("UTC"));
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#addHour(Date, int)} with empty parameter date.
     */
    @Test
    public void testAddHour_withEmptyDate_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                DateUtil.addHour(null, 1));
        assertThat(ex.getMessage(), equalTo("Parameter date can't be null"));
    }

    /**
     * Test method for
     * {@link DateUtil#addMinute(Date, int)}.
     */
    @Test
    public void testAddMinute() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, 1);

        Date actualDate = DateUtil.addMinute(date, 1);
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#addMinute(Date, int, TimeZone)}.
     */
    @Test
    public void testAddMinute_withTimeZone() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, 1);

        Date actualDate = DateUtil.addMinute(date, 1, TimeZone.getTimeZone("UTC"));
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#addMinute(Date, int)} with empty parameter date.
     */
    @Test
    public void testAddMinute_withEmptyDate_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                DateUtil.addMinute(null, 1));
        assertThat(ex.getMessage(), equalTo("Parameter date can't be null"));
    }

    /**
     * Test method for
     * {@link DateUtil#addSecond(Date, int)}.
     */
    @Test
    public void testAddSecond() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, 1);

        Date actualDate = DateUtil.addSecond(date, 1);
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#addSecond(Date, int, TimeZone)}.
     */
    @Test
    public void testAddSecond_withTimeZone() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, 1);

        Date actualDate = DateUtil.addSecond(date, 1, TimeZone.getTimeZone("UTC"));
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#addSecond(Date, int)} with empty parameter date.
     */
    @Test
    public void testAddSecond_withEmptyDate_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                DateUtil.addSecond(null, 1));
        assertThat(ex.getMessage(), equalTo("Parameter date can't be null"));
    }
}
