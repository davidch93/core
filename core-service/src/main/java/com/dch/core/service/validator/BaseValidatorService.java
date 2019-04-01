package com.dch.core.service.validator;

/**
 * Service used to validate object. Extends this interface if you want to create
 * custom validator services.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jul 28, 2017
 * @since 1.0.0-SNAPSHOT
 */
public interface BaseValidatorService {

    /**
     * Method used to check if code already exist or not.
     *
     * @param code {@link String} Code.
     * @return {@link boolean} true if exist and vice versa.
     */
    boolean isValidCode(String code);
}
