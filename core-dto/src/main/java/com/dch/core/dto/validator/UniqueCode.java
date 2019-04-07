package com.dch.core.dto.validator;

import com.dch.core.dto.validator.constraint.UniqueCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validate that the annotated string is a unique code.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@Documented
@Constraint(validatedBy = UniqueCodeValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface UniqueCode {

    String serviceName();

    String message() default "msg.error.field.common.code.exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
