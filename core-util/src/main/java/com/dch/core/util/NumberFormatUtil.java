package com.dch.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Classes that provides function to manipulate format number (currency).
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Apr 22, 2017
 */
public class NumberFormatUtil {

	public static final String DECIMAL_FORMAT = "#,###,###,###,##0.00";
	public static final String DECIMAL_FORMAT_NO_COMMA = "############0.00";

	/**
	 * Method used to format BigDecimal data type to String. Using format type
	 * #,###,###,###,##0.00
	 * 
	 * @param number
	 *            {@link BigDecimal}
	 * @return {@link String} #,###,###,###,##0.00
	 * @throws Exception
	 *             Number can't be null.
	 */
	public static String formatAmount(BigDecimal number) throws Exception {
		if (number == null)
			throw new Exception("Number can't be null");

		NumberFormat nf = new DecimalFormat(DECIMAL_FORMAT);
		return nf.format(number);
	}

	/**
	 * Method used to format BigDecimal data type to String without comma. Using
	 * format type ############0.00
	 * 
	 * @param number
	 *            {@link BigDecimal}
	 * @return {@link String}
	 * @throws Exception
	 *             Number can't be null.
	 */
	public static String formatAmountNoComma(BigDecimal number) throws Exception {
		if (number == null)
			throw new Exception("Number can't be null");

		DecimalFormat formatter = new DecimalFormat(DECIMAL_FORMAT_NO_COMMA);
		return formatter.format(number);
	}

	/**
	 * Method used to format double data type to String. Using format type
	 * #,###,###,###,##0.00
	 * 
	 * @param number
	 *            {@link double}
	 * @return {@link String}
	 */
	public static String formatAmount(double number) {
		DecimalFormat formatter = new DecimalFormat(DECIMAL_FORMAT);
		return formatter.format(number);
	}

	/**
	 * Method used to format double data type to String without comma. using
	 * format type ############0.00
	 * 
	 * @param number
	 *            {@link double}
	 * @return {@link String}
	 */
	public static String formatAmountNoComma(double number) {
		DecimalFormat formatter = new DecimalFormat(DECIMAL_FORMAT_NO_COMMA);
		return formatter.format(number);
	}

	/**
	 * Method used to format String data type to Long.
	 * 
	 * @param value
	 *            {@link String}
	 * @return {@link Long}
	 * @throws Exception
	 *             Value can't be null.
	 */
	public static Long parseToLong(String value) throws Exception {
		if (TextUtil.isEmpty(value))
			throw new Exception("Value can't be null");

		return Long.valueOf(value);
	}

	/**
	 * Method used to format String data type to Integer.
	 * 
	 * @param value
	 *            {@link String}
	 * @return {@link Integer}
	 * @throws Exception
	 *             Value can't be null.
	 */
	public static Integer parseToInteger(String value) throws Exception {
		if (TextUtil.isEmpty(value))
			throw new Exception("Value can't be null");

		return Integer.parseInt(value);
	}
}
