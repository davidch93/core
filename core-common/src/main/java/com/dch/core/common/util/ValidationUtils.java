package com.dch.core.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Objects;
import java.util.Set;

/**
 * Utility class for model validation based on {@link Validator}.
 *
 * @author Sayid Sidqi
 * @version 2.0.0
 * @since 2.0.0
 */
public final class ValidationUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ValidationUtils.class);

    private static Validator validator;

    /**
     * Validates all constraints on given {@code model}.
     *
     * @param model  model to validate (pojo, dto, business object, etc)
     * @param groups the group(s) targeted for validation (defaults to {@link javax.validation.groups.Default})
     * @param <M>    type of model to validate
     */
    public static <M> void validate(M model, Class<?>... groups) {
        Set<ConstraintViolation<M>> violations = validator.validate(model, groups);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    /**
     * Set {@link Validator} bean.
     * This should be called once in configuration class only.
     *
     * @param validator the validator bean
     */
    public static void setValidator(Validator validator) {
        if (!Objects.isNull(ValidationUtils.validator)) {
            LOG.warn("Validator already set. This instance {} is ignored", validator);
            return;
        }
        ValidationUtils.validator = validator;
    }

}