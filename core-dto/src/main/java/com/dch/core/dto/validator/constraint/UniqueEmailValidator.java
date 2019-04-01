package com.dch.core.dto.validator.constraint;

import com.dch.core.dto.validator.UniqueEmail;
import com.dch.core.service.validator.BaseValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Checks that a given string is a unique email.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jul 27, 2017
 * @since 1.0.0-SNAPSHOT
 */
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private ApplicationContext applicationContext;

    private String serviceName;

    @Override
    public void initialize(UniqueEmail annotation) {
        this.serviceName = annotation.serviceName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0)
            return false;

        BaseValidatorService validatorService = applicationContext.getBean(serviceName, BaseValidatorService.class);
        return validatorService.isValidCode(value);
    }
}
