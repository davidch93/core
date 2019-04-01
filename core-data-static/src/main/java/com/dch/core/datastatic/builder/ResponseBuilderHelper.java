package com.dch.core.datastatic.builder;

import com.dch.core.datastatic.GenericStatus;
import com.dch.core.datastatic.response.GenericResponse;

/**
 * Helper class that implements {@link GenericBuilder} to provide base builder
 * information to generate response.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jul 24, 2017
 * @since 1.0.0-SNAPSHOT
 */
public abstract class ResponseBuilderHelper implements GenericBuilder<GenericResponse> {

    /**
     * Method used to build data.
     *
     * @param data {@link Object} Content of data.
     * @return {@link ResponseBuilderHelper} Builder helper.
     */
    public abstract ResponseBuilderHelper setData(Object data);

    /**
     * Method used to build generic status.
     *
     * @param genericStatus {@link GenericStatus}
     * @return {@link ResponseBuilderHelper} Builder helper.
     */
    public abstract ResponseBuilderHelper setGenericStatus(GenericStatus genericStatus);

    /**
     * Method used to build arguments.
     *
     * @param args {@link Object}[] Additional information.
     * @return {@link ResponseBuilderHelper} Builder helper.
     */
    public abstract ResponseBuilderHelper setArgs(Object[] args);
}
