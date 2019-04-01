package com.dch.core.dto.validator.constraint;

import com.dch.core.dto.validator.NotEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check that a character sequence's (e.g. string) trimmed length is not empty.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jul 27, 2017
 * @since 1.0.0-SNAPSHOT
 */
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, CharSequence> {

    @Override
    public void initialize(NotEmpty annotation) {
    }

    /**
     * Checks that the trimmed string is not empty.
     *
     * @param charSequence The character sequence to validate.
     * @param context      context in which the constraint is evaluated.
     * @return Returns <code>true</code> if the string is <code>null</code> or
     * the length of <code>charSequence</code> between the specified
     * <code>min</code> and <code>max</code> values (inclusive),
     * <code>false</code> otherwise.
     */
    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0)
            return false;

        return value.toString().trim().length() > 0;
    }

}
