package com.dch.core.dto.validator;

import com.dch.core.dto.validator.constraint.FieldMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validation annotation to validate that 2 fields have the same value. An array
 * of fields and their matching confirmation fields can be supplied.
 *
 * <p> Example, compare 1 pair of fields:
 * <pre>
 * {@literal @}FieldMatch(baseField = "password", matchField = "confirmPwd", message = "The password fields must match")
 * </pre>
 *
 * <p> Example, compare more than 1 pair of fields:
 * <pre>
 * {@literal @}FieldMatch.List({
 *   {@literal @}FieldMatch(baseField = "password", matchField = "confPwd", message = "The password fields must match"),
 *   {@literal @}FieldMatch(baseField = "email", matchField = "confirmEmail", message = "The email fields must match")})
 * </pre>
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@Documented
@Constraint(validatedBy = FieldMatchValidator.class)
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface FieldMatch {

    String baseField();

    String matchField();

    String message() default "msg.error.field.common.notmatch";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several <code>@FieldMatch</code> annotations on the same element
     *
     * @see FieldMatch
     */
    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        FieldMatch[] value();
    }
}
