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
     * Method used to format BigDecimal data type to String. Using format type
     * #,###,###,###,##0.00
     *
     * @param number {@link BigDecimal}
     * @return {@link String} #,###,###,###,##0.00
     */
    public static String formatAmount(BigDecimal number) {
        Assert.notNull(number, "Number can't be null");

        NumberFormat nf = new DecimalFormat(DECIMAL_FORMAT);
        return nf.format(number);
    }

    /**
     * Method used to format BigDecimal data type to String without comma. Using
     * format type ############0.00
     *
     * @param number {@link BigDecimal}
     * @return {@code String}
     */
    public static String formatAmountNoComma(BigDecimal number) {
        Assert.notNull(number, "Number can't be null");

        DecimalFormat formatter = new DecimalFormat(DECIMAL_FORMAT_NO_COMMA);
        return formatter.format(number);
    }

    /**
     * Method used to format double data type to String. Using format type
     * #,###,###,###,##0.00
     *
     * @param number {@link double}
     * @return {@code String}
     */
    public static String formatAmount(double number) {
        DecimalFormat formatter = new DecimalFormat(DECIMAL_FORMAT);
        return formatter.format(number);
    }

    /**
     * Method used to format double data type to String without comma. using
     * format type ############0.00
     *
     * @param number {@link double}
     * @return {@code String}
     */
    public static String formatAmountNoComma(double number) {
        DecimalFormat formatter = new DecimalFormat(DECIMAL_FORMAT_NO_COMMA);
        return formatter.format(number);
    }

    /**
     * Method used to format String data type to Long.
     *
     * @param value {@link String}
     * @return {@code Long}
     */
    public static Long parseToLong(String value) {
        Assert.hasLength(value, "Value can't be null");

        return Long.valueOf(value);
    }

    /**
     * Method used to format String data type to Integer.
     *
     * @param value {@link String}
     * @return {@code Integer}
     */
    public static Integer parseToInteger(String value) {
        Assert.hasLength(value, "Value can't be null");

        return Integer.parseInt(value);
    }
}
