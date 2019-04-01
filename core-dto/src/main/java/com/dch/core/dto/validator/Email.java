package com.dch.core.dto.validator;

import com.dch.core.dto.validator.constraint.EmailValidator;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validate that the annotated string is a well-formed email address.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jul 27, 2017
 * @since 1.0.0-SNAPSHOT
 */
@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface Email {

    String message() default "msg.error.field.common.email.notvalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return an additional regular expression the annotated string must match.
     * The default is any string ('.*')
     */
    @OverridesAttribute(constraint = Pattern.class, name = "regexp")
    String regexp() default ".*";

    /**
     * @return used in combination with {@link #regexp()} in order to specify a
     * regular expression option
     */
    @OverridesAttribute(constraint = Pattern.class, name = "flags")
    Pattern.Flag[] flags() default {};

    /**
     * Defines several {@code @Email} annotations on the same element.
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        Email[] value();
    }
}
