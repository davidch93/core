package com.dch.core.dto.validator;

import com.dch.core.dto.validator.constraint.NotEmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validate that the annotated Object is not {@code null} or empty.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jul 27, 2017
 * @since 1.0.0-SNAPSHOT
 */
@Documented
@Constraint(validatedBy = NotEmptyValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface NotEmpty {

    String message() default "msg.error.field.common.notempty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several {@code @NotNull} annotations on the same element.
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        NotEmpty[] value();
    }
}
