package com.dch.core.dto.validator.constraint;

import com.dch.core.dto.validator.FieldMatch;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

/**
 * Checks that a given object have 2 fields with the same value.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see javax.validation.ConstraintValidator
 * @since 1.0.0
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
     * Get field value from Object using field name.
     *
     * @param object    the base object.
     * @param fieldName the field name.
     * @return the field.
     * @throws Exception if there are error during reflect object.
     */
    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        Field declaredField = clazz.getDeclaredField(fieldName);
        declaredField.setAccessible(true);
        return declaredField.get(object);
    }
}
