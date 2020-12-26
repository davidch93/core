package com.dch.core.common.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class used to test all methods in the {@link NumberFormatUtil} class.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public class NumberFormatUtilTest {

    /**
     * Test method for
     * {@link NumberFormatUtil#formatAmount(BigDecimal)}.
     */
    @Test
    public void testFormatAmountBigDecimal() {
        String actual = NumberFormatUtil.formatAmount(BigDecimal.valueOf(100000));
        assertThat(actual, is(equalTo("100,000.00")));
    }

    /**
     * Test method for
     * {@link NumberFormatUtil#formatAmount(BigDecimal)} with empty number.
     */
    @Test
    public void testFormatAmountBigDecimal_withEmptyNumber_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                NumberFormatUtil.formatAmount(null));
        assertThat(ex.getMessage(), equalTo("Number can't be null"));
    }

    /**
     * Test method for
     * {@link NumberFormatUtil#formatAmountNoComma(BigDecimal)}.
     */
    @Test
    public void testFormatAmountNoCommaBigDecimal() {
        String actual = NumberFormatUtil.formatAmountNoComma(BigDecimal.valueOf(100000));
        assertThat(actual, is(equalTo("100000.00")));
    }

    /**
     * Test method for
     * {@link NumberFormatUtil#formatAmountNoComma(BigDecimal)} with empty number.
     */
    @Test
    public void testFormatAmountNoCommaBigDecimal_withEmptyNumber_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                NumberFormatUtil.formatAmountNoComma(null));
        assertThat(ex.getMessage(), equalTo("Number can't be null"));
    }

    /**
     * Test method for
     * {@link NumberFormatUtil#formatAmount(double)}.
     */
    @Test
    public void testFormatAmountDouble() {
        String actual = NumberFormatUtil.formatAmount(100000);
        assertThat(actual, is(equalTo("100,000.00")));
    }

    /**
     * Test method for
     * {@link NumberFormatUtil#formatAmountNoComma(double)}.
     */
    @Test
    public void testFormatAmountNoCommaDouble() {
        String actual = NumberFormatUtil.formatAmountNoComma(100000);
        assertThat(actual, is(equalTo("100000.00")));
    }

    /**
     * Test method for
     * {@link NumberFormatUtil#parseToLong(String)}.
     */
    @Test
    public void testParseToLong() {
        Long actual = NumberFormatUtil.parseToLong("100000");
        assertThat(actual, is(equalTo(100000L)));
    }

    /**
     * Test method for
     * {@link NumberFormatUtil#parseToLong(String)} with empty value.
     */
    @Test
    public void testParseToLong_withEmptyValue_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                NumberFormatUtil.parseToLong(null));
        assertThat(ex.getMessage(), equalTo("Value can't be null"));
    }

    /**
     * Test method for
     * {@link NumberFormatUtil#parseToInteger(String)}.
     */
    @Test
    public void testParseToInteger() {
        int actual = NumberFormatUtil.parseToInteger("100000");
        assertThat(actual, is(equalTo(100000)));
    }

    /**
     * Test method for
     * {@link NumberFormatUtil#parseToInteger(String)} with empty value.
     */
    @Test
    public void testParseToInteger_withEmptyValue_thenExpectThrowException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                NumberFormatUtil.parseToInteger(null));
        assertThat(ex.getMessage(), equalTo("Value can't be null"));
    }
}
