package com.dch.core.common.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test class used to test all methods in the {@link NumberFormatUtil} class.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public class NumberFormatUtilTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * Test method for
     * {@link NumberFormatUtil#formatAmount(java.math.BigDecimal)}.
     */
    @Test
    public void testFormatAmountBigDecimal() {
        String actual = NumberFormatUtil.formatAmount(BigDecimal.valueOf(100000));
        assertThat(actual, is(equalTo("100,000.00")));
    }

    /**
     * Test method for
     * {@link NumberFormatUtil#formatAmount(java.math.BigDecimal)}
     * with empty number.
     */
    @Test
    public void testFormatAmountBigDecimalWithEmptyNumber() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Number can't be null");
        NumberFormatUtil.formatAmount(null);
    }

    /**
     * Test method for
     * {@link NumberFormatUtil#formatAmountNoComma(java.math.BigDecimal)}.
     */
    @Test
    public void testFormatAmountNoCommaBigDecimal() {
        String actual = NumberFormatUtil.formatAmountNoComma(BigDecimal.valueOf(100000));
        assertThat(actual, is(equalTo("100000.00")));
    }

    /**
     * Test method for
     * {@link NumberFormatUtil#formatAmountNoComma(java.math.BigDecimal)}
     * with empty number.
     */
    @Test
    public void testFormatAmountNoCommaBigDecimalWithEmptyNumber() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Number can't be null");
        NumberFormatUtil.formatAmountNoComma(null);
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
     * {@link NumberFormatUtil#parseToLong(java.lang.String)}.
     */
    @Test
    public void testParseToLong() {
        Long actual = NumberFormatUtil.parseToLong("100000");
        assertThat(actual, is(equalTo(100000L)));
    }

    /**
     * Test method for
     * {@link NumberFormatUtil#parseToLong(java.lang.String)}
     * with empty value.
     */
    @Test
    public void testParseToLongWithEmptyValue() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Value can't be null");
        NumberFormatUtil.parseToLong(null);
    }

    /**
     * Test method for
     * {@link NumberFormatUtil#parseToInteger(java.lang.String)}.
     */
    @Test
    public void testParseToInteger() {
        int actual = NumberFormatUtil.parseToInteger("100000");
        assertThat(actual, is(equalTo(100000)));
    }

    /**
     * Test method for
     * {@link NumberFormatUtil#parseToInteger(java.lang.String)}
     * with empty value.
     */
    @Test
    public void testParseToIntegerWithEmptyValue() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Value can't be null");
        NumberFormatUtil.parseToInteger(null);
    }
}
