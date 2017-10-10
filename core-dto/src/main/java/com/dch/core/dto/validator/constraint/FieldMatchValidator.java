package com.dch.core.dto.validator.constraint;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.dch.core.dto.validator.FieldMatch;

/**
 * Checks that a given object have 2 fields with the same value.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jul 27, 2017
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

	private String baseField;
	private String matchField;

	@Override
	public void initialize(FieldMatch annotation) {
		this.baseField = annotation.baseField();
		this.matchField = annotation.matchField();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (baseField == null && matchField == null)
			return false;

		try {
			Object baseFieldValue = getFieldValue(value, baseField);
			Object matchFieldValue = getFieldValue(value, matchField);
			return baseFieldValue != null && baseFieldValue.equals(matchFieldValue);
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * Method used to get field value from Object using field name.
	 * 
	 * @param object
	 *            {@link Object} Base object.
	 * @param fieldName
	 *            {@link String} Field name.
	 * @return {@link Object} Field.
	 * @throws Exception
	 *             if there are error during reflect object.
	 */
	private Object getFieldValue(Object object, String fieldName) throws Exception {
		Class<?> clazz = object.getClass();
		Field declaredField = clazz.getDeclaredField(fieldName);
		declaredField.setAccessible(true);
		return declaredField.get(object);
	}
}
