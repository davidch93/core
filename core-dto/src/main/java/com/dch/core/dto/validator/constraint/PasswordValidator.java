package com.dch.core.dto.validator.constraint;

import com.dch.core.dto.validator.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Checks that a given character sequence (e.g. string) is a regular password
 * expression.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see javax.validation.ConstraintValidator
 * @since 1.0.0
 */
public class PasswordValidator implements ConstraintValidator<Password, CharSequence> {

    /**
     * (?=.*\d)		# must contains one digit from 0-9 <br/>
     * (?=.*[a-z])	# must contains one lowercase characters <br/>
     * (?=.*[A-Z])	# must contains one uppercase characters <br/>
     * (?=.*[@#$%])	# must contains one special symbols in the list "@#$%" <br/>
     * . 			# match anything with previous condition checking <br/>
     * {8,20} 		# length at least 6 characters and maximum of 20 <br/>
     */
    private static final String PASSWORD_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    @Override
    public void initialize(Password annotation) {
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0)
            return false;

        Matcher matcher = PASSWORD_PATTERN.matcher(value);
        return matcher.matches();
    }
}
