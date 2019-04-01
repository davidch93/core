/**
 *
 */
package com.dch.core.util.test;

import com.dch.core.util.NumberFormatUtil;
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
 * @version 1.0.0-SNAPSHOT
 * @since 1.0.0-SNAPSHOT
 * @updated Apr 22, 2017
 */
public class NumberFormatUtilTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * Test method for
     * {@link com.dch.core.util.NumberFormatUtil#formatAmount(java.math.BigDecimal)}.
     *
     * @throws Exception
     */
    @Test
    public void testFormatAmountBigDecimal() throws Exception {
        String actual = NumberFormatUtil.formatAmount(BigDecimal.valueOf(100000));
        assertThat(actual, is(equalTo("100,000.00")));
    }

    /**
     * Test method for
     * {@link com.dch.core.util.NumberFormatUtil#formatAmount(java.math.BigDecimal)}
     * with empty number.
     *
     * @throws Exception
     */
    @Test
    public void testFormatAmountBigDecimalWithEmptyNumber() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Number can't be null");
        NumberFormatUtil.formatAmount(null);
    }

    /**
     * Test method for
     * {@link com.dch.core.util.NumberFormatUtil#formatAmountNoComma(java.math.BigDecimal)}.
     *
     * @throws Exception
     */
    @Test
    public void testFormatAmountNoCommaBigDecimal() throws Exception {
        String actual = NumberFormatUtil.formatAmountNoComma(BigDecimal.valueOf(100000));
        assertThat(actual, is(equalTo("100000.00")));
    }

    /**
     * Test method for
     * {@link com.dch.core.util.NumberFormatUtil#formatAmountNoComma(java.math.BigDecimal)}
     * with empty number.
     *
     * @throws Exception
     */
    @Test
    public void testFormatAmountNoCommaBigDecimalWithEmptyNumber() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Number can't be null");
        NumberFormatUtil.formatAmountNoComma(null);
    }

    /**
     * Test method for
     * {@link com.dch.core.util.NumberFormatUtil#formatAmount(double)}.
     */
    @Test
    public void testFormatAmountDouble() {
        String actual = NumberFormatUtil.formatAmount(100000);
        assertThat(actual, is(equalTo("100,000.00")));
    }

    /**
     * Test method for
     * {@link com.dch.core.util.NumberFormatUtil#formatAmountNoComma(double)}.
     */
    @Test
    public void testFormatAmountNoCommaDouble() {
        String actual = NumberFormatUtil.formatAmountNoComma(100000);
        assertThat(actual, is(equalTo("100000.00")));
    }

    /**
     * Test method for
     * {@link com.dch.core.util.NumberFormatUtil#parseToLong(java.lang.String)}.
     *
     * @throws Exception
     */
    @Test
    public void testParseToLong() throws Exception {
        Long actual = NumberFormatUtil.parseToLong("100000");
        assertThat(actual, is(equalTo(100000L)));
    }

    /**
     * Test method for
     * {@link com.dch.core.util.NumberFormatUtil#parseToLong(java.lang.String)}
     * with empty value.
     *
     * @throws Exception
     */
    @Test
    public void testParseToLongWithEmptyValue() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Value can't be null");
        NumberFormatUtil.parseToLong(null);
    }

    /**
     * Test method for
     * {@link com.dch.core.util.NumberFormatUtil#parseToInteger(java.lang.String)}.
     *
     * @throws Exception
     */
    @Test
    public void testParseToInteger() throws Exception {
        int actual = NumberFormatUtil.parseToInteger("100000");
        assertThat(actual, is(equalTo(100000)));
    }

    /**
     * Test method for
     * {@link com.dch.core.util.NumberFormatUtil#parseToInteger(java.lang.String)}
     * with empty value.
     *
     * @throws Exception
     */
    @Test
    public void testParseToIntegerWithEmptyValue() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Value can't be null");
        NumberFormatUtil.parseToInteger(null);
    }
}
