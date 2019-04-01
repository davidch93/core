package com.dch.core.dto.validator.constraint;

import com.dch.core.dto.validator.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Check that a string is valid phone number.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jul 27, 2017
 * @since 1.0.0-SNAPSHOT
 */
public class PhoneValidator implements ConstraintValidator<Phone, CharSequence> {

    /**
     * Validate phone numbers of format "1234567890"
     */
    private static final String NUMBER_REGEX = "\\d{10}";

    /**
     * Validate phone number with -, . or spaces. <br/>
     * Ex. 123-456-7890
     */
    private static final String SPECIAL_CHAR_REGEX = "\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}";

    /**
     * Validate phone number with extension length from 3 to 5. <br/>
     * Ex. 123-456-7890 x1234, 123-456-7890 ext1234
     */
    private static final String EXTENSION_REGEX = "\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}";

    /**
     * Validate phone number where area code is in braces (). <br/>
     * Ex. (123)-456-7890
     */
    private static final String AREA_CODE_REGEX = "\\(\\d{3}\\)-\\d{3}-\\d{4}";

    private static final Pattern NUMBER_PATTERN = Pattern.compile(NUMBER_REGEX);
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile(SPECIAL_CHAR_REGEX);
    private static final Pattern EXTENSION_PATTERN = Pattern.compile(EXTENSION_REGEX);
    private static final Pattern AREA_CODE_PATTERN = Pattern.compile(AREA_CODE_REGEX);

    @Override
    public void initialize(Phone annotation) {
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0)
            return false;

        if (NUMBER_PATTERN.matcher(value).matches())
            return true;
        if (SPECIAL_CHAR_PATTERN.matcher(value).matches())
            return true;
        if (EXTENSION_PATTERN.matcher(value).matches())
            return true;
        if (AREA_CODE_PATTERN.matcher(value).matches())
            return true;

        return false;
    }
}
