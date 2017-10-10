package com.dch.core.dto.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.dch.core.dto.validator.constraint.EnumValueValidator;

/**
 * Validate that the annotated string is valid value from Enum class.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jul 27, 2017
 */
@Documented
@Constraint(validatedBy = EnumValueValidator.class)
@Target({ METHOD, FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface EnumValue {

	Class<? extends Enum<?>> enumClass();

	boolean ignoreCase() default false;

	String message() default "msg.error.field.common.enum.notvalid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
