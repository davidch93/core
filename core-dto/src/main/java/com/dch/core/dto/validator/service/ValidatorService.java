package com.dch.core.dto.validator.service;

/**
 * Service used to validate object. Implement this interface if you want to create
 * custom validator services.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public interface ValidatorService {

    /**
     * Check whether code already exist or not.
     *
     * @param code the code.
     * @return {@link boolean} true if exist and vice versa.
     */
    boolean isValidCode(String code);
}
