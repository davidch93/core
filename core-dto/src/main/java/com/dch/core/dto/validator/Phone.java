package com.dch.core.dto.validator;

import com.dch.core.dto.validator.constraint.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validate that the annotated string is valid phone number.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface Phone {

    String message() default "msg.error.field.common.phone.notvalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
