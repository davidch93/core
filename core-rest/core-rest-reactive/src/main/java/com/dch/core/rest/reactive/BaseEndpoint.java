package com.dch.core.rest.reactive;

import com.dch.core.dto.response.builder.ResponseBuilder;
import com.dch.core.rest.reactive.exception.RestException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 * Class that define common function for all endpoint.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public abstract class BaseEndpoint {

    @Autowired
    private MessageSource messageSource;

    /**
     * Method used to Copy bean properties.
     *
     * @param source {@link Object} source data
     * @param clazz  {@code Class<T>} of target
     * @param <T>    the type of object data
     * @return the {@link T} target object
     */
    protected <T> T copyProperties(Object source, Class<T> clazz) {
        return copyProperties(source, clazz, null);
    }

    /**
     * Method used to Copy bean properties.
     *
     * @param source         {@link Object} source data
     * @param clazz          {@code Class<T>} of target
     * @param ignoreProperty {@link String[]} ignore parameter
     * @param <T>            the type of object data
     * @return the {@link T} target object
     * @throws RestException if the class or its nullary constructor is not accessible or
     *                       if this Class represents an abstract class, an interface, an
     *                       array class, a primitive type, or void; or if the class has
     *                       no nullary constructor; or if the instantiation fails for
     *                       some other reason.
     */
    protected <T> T copyProperties(Object source, Class<T> clazz, String[] ignoreProperty) {
        try {
            if (source == null)
                return null;

            T target = clazz.newInstance();
            if (ignoreProperty != null)
                BeanUtils.copyProperties(source, target, ignoreProperty);
            else
                BeanUtils.copyProperties(source, target);

            return target;
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RestException("Error occurred while copying properties!", ex);
        }
    }

    /**
     * Method used to get response builder to build a generic response.
     *
     * @param clazz {@code Class<T>} of target
     * @param <T>   the type of object data
     * @return the {@link ResponseBuilder}
     */
    protected <T> ResponseBuilder<T> getResponseBuilder(Class<T> clazz) {
        return new ResponseBuilder<>(messageSource);
    }
}
