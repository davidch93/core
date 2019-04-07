package com.dch.core.dto.validator;

import com.dch.core.dto.validator.constraint.EnumValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validate that the annotated string is valid value from Enum class.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@Documented
@Constraint(validatedBy = EnumValueValidator.class)
@Target({METHOD, FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface EnumValue {

    Class<? extends Enum<?>> enumClass();

    boolean ignoreCase() default false;

    String message() default "msg.error.field.common.enum.notvalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
