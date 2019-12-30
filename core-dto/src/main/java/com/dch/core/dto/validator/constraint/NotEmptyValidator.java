package com.dch.core.dto.validator.constraint;

import com.dch.core.dto.validator.NotEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check that a character sequence's (e.g. string) trimmed length is not empty.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see javax.validation.ConstraintValidator
 * @since 1.0.0
 */
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, CharSequence> {

    @Override
    public void initialize(NotEmpty annotation) {
    }

    /**
     * Checks whether the trimmed string is not empty.
     *
     * @param charSequence the character sequence to validate.
     * @param context      the context in which the constraint is evaluated.
     * @return Returns <code>true</code> if the string is <code>null</code> or
     * the length of <code>charSequence</code> between the specified
     * <code>min</code> and <code>max</code> values (inclusive),
     * <code>false</code> otherwise.
     */
    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext context) {
        if (charSequence == null || charSequence.length() == 0)
            return false;

        return charSequence.toString().trim().length() > 0;
    }

}
