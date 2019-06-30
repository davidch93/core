package com.dch.core.common.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test class used to test all methods in the {@link DateUtil} class.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public class DateUtilTest {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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
    public void testFormatDateWithTimeZone() {
        String actualDate = DateUtil.formatDate(new Date(), DATE_FORMAT, TimeZone.getTimeZone("UTC"));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String expectedDate = simpleDateFormat.format(new Date());

        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#formatDate(Date, String)}
     * with empty parameter date.
     */
    @Test
    public void testFormatDateEmptyDate() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Parameter date can't be null");
        DateUtil.formatDate(null, DATE_FORMAT);
    }

    /**
     * Test method for
     * {@link DateUtil#formatDate(Date, String)}
     * with empty parameter format.
     */
    @Test
    public void testFormatDateEmptyFormat() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Parameter format can't be empty");
        DateUtil.formatDate(new Date(), null);
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
    public void testParseDateWithTimeZone() throws Exception {
        String date = "2019-01-01 00:00:00";
        Date actualDate = DateUtil.parseDate(date, DATE_FORMAT, TimeZone.getTimeZone("UTC"));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date expectedDate = simpleDateFormat.parse(date);

        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#parseDate(String, String)}
     * with empty parameter date.
     */
    @Test
    public void testParseDateEmptyDate() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Parameter date can't be empty");
        DateUtil.parseDate(null, DATE_FORMAT);
    }

    /**
     * Test method for
     * {@link DateUtil#parseDate(String, String)}
     * with empty parameter format.
     */
    @Test
    public void testParseDateEmptyFormat() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Parameter format can't be empty");
        DateUtil.parseDate(new Date().toString(), null);
    }

    /**
     * Test method for
     * {@link DateUtil#setBeginningOfDay(Date)}.
     */
    @Test
    public void testSetBeginningOfDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date actualDate = DateUtil.setBeginningOfDay(new Date());
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#setBeginningOfDay(Date, TimeZone)}.
     */
    @Test
    public void testSetBeginningOfDayWithTimeZone() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date actualDate = DateUtil.setBeginningOfDay(new Date(), TimeZone.getTimeZone("UTC"));
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#setBeginningOfDay(Date)} with
     * empty parameter date.
     */
    @Test
    public void testSetBeginningOfDayWithEmptyDate() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Parameter date can't be null");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date actualDate = DateUtil.setBeginningOfDay(null);
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#setEndingOfDay(Date)}.
     */
    @Test
    public void testSetEndingOfDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        Date actualDate = DateUtil.setEndingOfDay(new Date());
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#setEndingOfDay(Date, TimeZone)}.
     */
    @Test
    public void testSetEndingOfDayWithTimeZone() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        Date actualDate = DateUtil.setEndingOfDay(new Date(), TimeZone.getTimeZone("UTC"));
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#setEndingOfDay(Date)} with
     * empty parameter date.
     */
    @Test
    public void testSetEndingOfDayWithEmptDate() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Parameter date can't be null");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        Date actualDate = DateUtil.setEndingOfDay(null);
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#addDate(Date, int)}.
     */
    @Test
    public void testAddDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);

        Date actualDate = DateUtil.addDate(new Date(), 1);
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#addDate(Date, int, TimeZone)}.
     */
    @Test
    public void testAddDateWithTimeZone() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);

        Date actualDate = DateUtil.addDate(new Date(), 1, TimeZone.getTimeZone("UTC"));
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#addDate(Date, int)} with empty
     * parameter date.
     */
    @Test
    public void testAddDateWithEmptyDate() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Parameter date can't be null");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);

        Date actualDate = DateUtil.addDate(null, 1);
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#addHour(Date, int)}.
     */
    @Test
    public void testAddHour() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, 1);

        Date actualDate = DateUtil.addHour(new Date(), 1);
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#addHour(Date, int, TimeZone)}.
     */
    @Test
    public void testAddHourWithTimeZone() {
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
     * {@link DateUtil#addHour(Date, int)} with empty
     * parameter date.
     */
    @Test
    public void testAddHourWithEmptyDate() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Parameter date can't be null");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, 1);

        Date actualDate = DateUtil.addHour(null, 1);
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#addMinute(Date, int)}.
     */
    @Test
    public void testAddMinute() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 1);

        Date actualDate = DateUtil.addMinute(new Date(), 1);
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#addMinute(Date, int, TimeZone)}.
     */
    @Test
    public void testAddMinuteWithTimeZone() {
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
     * {@link DateUtil#addMinute(Date, int)}with
     * empty parameter date.
     */
    @Test
    public void testAddMinuteWithEmptyDate() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Parameter date can't be null");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 1);

        Date actualDate = DateUtil.addMinute(null, 1);
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#addSecond(Date, int)}.
     */
    @Test
    public void testAddSecond() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, 1);

        Date actualDate = DateUtil.addSecond(new Date(), 1);
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

    /**
     * Test method for
     * {@link DateUtil#addSecond(Date, int, TimeZone)}.
     */
    @Test
    public void testAddSecondWithTimeZone() {
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
     * {@link DateUtil#addSecond(Date, int)} with
     * empty parameter date.
     */
    @Test
    public void testAddSecondWithEmptyDate() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Parameter date can't be null");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, 1);

        Date actualDate = DateUtil.addSecond(null, 1);
        Date expectedDate = calendar.getTime();
        assertThat(actualDate, is(equalTo(expectedDate)));
    }

}
