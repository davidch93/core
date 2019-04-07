package com.dch.core.security.jwt.service.impl;

import com.dch.core.dto.response.builder.ResponseBuilder;
import com.dch.core.security.jwt.service.SecurityDetailsService;
import org.springframework.context.MessageSource;

/**
 * This class serves as the Base class for all security details service - namely
 * to hold common service methods that they might all use.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see com.dch.core.security.jwt.service.SecurityDetailsService
 * @since 1.0.0
 */
public abstract class AbstractSecurityDetailsServiceImpl implements SecurityDetailsService {

    protected final MessageSource messageSource;

    protected AbstractSecurityDetailsServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public ResponseBuilder getSecurityResponseBuilder() {
        return new ResponseBuilder(messageSource);
    }
}
