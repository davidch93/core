package com.dch.core.scheduler.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

/**
 * Custom scheduler error handler by implementing {@link ErrorHandler}.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jun 16, 2017
 * @since 1.0.0-SNAPSHOT
 */
public class SchedulerErrorHandler implements ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerErrorHandler.class);

    private final String identityPrefix;

    public SchedulerErrorHandler(final String identityPrefix) {
        this.identityPrefix = identityPrefix;
    }

    @Override
    public void handleError(Throwable t) {
        LOGGER.error(String.format("[%s] %s", identityPrefix, t.getMessage()), t);
    }
}
