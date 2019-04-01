package com.dch.core.dto.validator;

import com.dch.core.dto.validator.constraint.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validate that the annotated string is a unique email.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jul 27, 2017
 * @since 1.0.0-SNAPSHOT
 */
@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface UniqueEmail {

    String serviceName();

    String message() default "msg.error.field.common.email.exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
