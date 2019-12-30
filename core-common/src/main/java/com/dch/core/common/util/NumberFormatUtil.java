package com.dch.core.common.util;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Classes that provides function to manipulate format number (currency).
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public class NumberFormatUtil {

    /**
     * Default value for decimal format.
     */
    public static final String DECIMAL_FORMAT = "#,###,###,###,##0.00";

    /**
     * Default value for decimal without comma format.
     */
    public static final String DECIMAL_FORMAT_NO_COMMA = "############0.00";

    /**
     * Format BigDecimal data type to String. Using format type #,###,###,###,##0.00
     *
     * @param number the {@link BigDecimal}
     * @return the number in format #,###,###,###,##0.00
     */
    public static String formatAmount(BigDecimal number) {
        Assert.notNull(number, "Number can't be null");

        NumberFormat nf = new DecimalFormat(DECIMAL_FORMAT);
        return nf.format(number);
    }

    /**
     * Format BigDecimal data type to String without comma. Using format type ############0.00
     *
     * @param number the {@link BigDecimal}
     * @return the number in format ############0.00
     */
    public static String formatAmountNoComma(BigDecimal number) {
        Assert.notNull(number, "Number can't be null");

        DecimalFormat formatter = new DecimalFormat(DECIMAL_FORMAT_NO_COMMA);
        return formatter.format(number);
    }

    /**
     * Format double data type to String. Using format type #,###,###,###,##0.00
     *
     * @param number the {@code double}
     * @return the number in format #,###,###,###,##0.00
     */
    public static String formatAmount(double number) {
        DecimalFormat formatter = new DecimalFormat(DECIMAL_FORMAT);
        return formatter.format(number);
    }

    /**
     * Format double data type to String without comma. Using format type ############0.00
     *
     * @param number the {@code double}
     * @return the number in format ############0.00
     */
    public static String formatAmountNoComma(double number) {
        DecimalFormat formatter = new DecimalFormat(DECIMAL_FORMAT_NO_COMMA);
        return formatter.format(number);
    }

    /**
     * Format String data type to Long.
     *
     * @param value the string value.
     * @return the long value.
     */
    public static Long parseToLong(String value) {
        Assert.hasLength(value, "Value can't be null");

        return Long.valueOf(value);
    }

    /**
     * Format String data type to Integer.
     *
     * @param value the string value.
     * @return the integer value.
     */
    public static Integer parseToInteger(String value) {
        Assert.hasLength(value, "Value can't be null");

        return Integer.parseInt(value);
    }
}
