package com.dch.core.util;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Classes that provides function to manipulate String.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Apr 22, 2017
 */
public class TextUtil {

	/**
	 * Method used to check if data request is not null and not empty.
	 * 
	 * @param object
	 *            {@link Object} data request.
	 * @return true if data request is exist and vice versa.
	 */
	public static boolean isExist(Object object) {
		if (object != null) {
			if (object instanceof String)
				return (((String) object).length() != 0);
			return true;
		}
		return false;
	}

	/**
	 * Method used to check if data request is empty or not.
	 * 
	 * @param object
	 *            {@link Object} data request.
	 * @return true if data request is empty and vice versa.
	 */
	public static boolean isEmpty(Object object) {
		if (object == null) {
			if (object instanceof String)
				return ((String) object).length() == 0;
			return true;
		}

		if (object instanceof String)
			return ((String) object).length() == 0;

		return false;
	}

	/**
	 * Method used to parse data request into string.
	 * 
	 * @param data
	 *            {@link Object} data request.
	 * @return data request in string.
	 * @throws Exception
	 *             Parameter can't be null.
	 */
	public static String parseToString(Object data) throws Exception {
		if (data == null)
			throw new Exception("Parameter can't be null");

		if (data instanceof Double)
			return ((Double) data).toString();
		else if (data instanceof Integer)
			return ((Integer) data).toString();
		else if (data instanceof Boolean)
			return ((Boolean) data).toString();
		else if (data instanceof BigDecimal)
			return ((BigDecimal) data).toString();
		else
			return (String) data;
	}

	/**
	 * Method used to generatedId (UUID).
	 * 
	 * @return Generated ID.
	 */
	public static String generateId() {
		return UUID.randomUUID().toString();
	}
}
