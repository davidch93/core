package com.dch.core.dto.validator;

import com.dch.core.dto.validator.constraint.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validate that the annotated string is a regular password expression.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface Password {

    String message() default "msg.error.field.common.password.notvalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
