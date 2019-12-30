package com.dch.core.dto.validator.constraint;

import com.dch.core.dto.validator.EnumValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Checks that a given string is valid value from Enum class.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see javax.validation.ConstraintValidator
 * @since 1.0.0
 */
public class EnumValueValidator implements ConstraintValidator<EnumValue, String> {

    private EnumValue annotation;

    @Override
    public void initialize(EnumValue annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0)
            return false;

        Object[] enumValues = this.annotation.enumClass().getEnumConstants();
        return enumValues != null && checkEnumValues(enumValues, value);
    }

    /**
     * Check whether value for validation exist in enum class.
     *
     * @param enumValues List of value in enum class.
     * @param value      the value for validation.
     * @return {@link boolean} true if value exist in enum class and vice versa.
     */
    private boolean checkEnumValues(Object[] enumValues, String value) {
        for (Object enumValue : enumValues) {
            if (value.equals(enumValue.toString())
                    || (this.annotation.ignoreCase() && value.equalsIgnoreCase(enumValue.toString()))) {
                return true;
            }
        }

        return false;
    }
}
