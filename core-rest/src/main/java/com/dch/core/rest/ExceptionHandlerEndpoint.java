package com.dch.core.rest;

import com.dch.core.datastatic.GeneralStatus;
import com.dch.core.dto.response.GeneralResponse;
import com.dch.core.rest.exception.RestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class that defined exception handler for all endpoints.
 *
 * @author david.christianto
 * @version 2.0.0
 * @see com.dch.core.rest.BaseEndpoint
 */
@RestControllerAdvice
public class ExceptionHandlerEndpoint extends BaseEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerEndpoint.class);

    private final MessageSource messageSource;

    public ExceptionHandlerEndpoint(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Method to handle HTTP message not readable exception.
     *
     * @param ex {@link HttpMessageNotReadableException}
     * @return {@link GeneralResponse} Response body of message not readable.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GeneralResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        logger.error("HTTP message error! " + GeneralStatus.MESSAGE_NOT_READABLE, ex);
        return getResponseBuilder()
                .setGeneralStatus(GeneralStatus.MESSAGE_NOT_READABLE)
                .build();
    }

    /**
     * Method to handle response method argument not valid exception.
     *
     * @param ex {@link MethodArgumentNotValidException}
     * @return {@link GeneralResponse} Response body of validation error.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GeneralResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.error("HTTP message error! " + GeneralStatus.ARGUMENT_NOT_VALID, ex);

        Map<String, String> errorsMap = ex.getBindingResult().getAllErrors().stream()
                .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(),
                        objectError -> messageSource.getMessage(Objects.requireNonNull(objectError.getDefaultMessage()),
                                objectError.getArguments(), LocaleContextHolder.getLocale()),
                        (objectError1, objectError2) -> objectError2));

        return getResponseBuilder()
                .setData(errorsMap)
                .setGeneralStatus(GeneralStatus.ARGUMENT_NOT_VALID)
                .build();
    }

    /**
     * Method to handle HTTP not found exception.
     *
     * @param ex {@link NoHandlerFoundException}
     * @return {@link GeneralResponse} Response body of not found exception.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GeneralResponse handleNoHandlerFoundException(NoHandlerFoundException ex) {
        logger.error("HTTP message error! " + GeneralStatus.NOT_FOUND, ex);
        return getResponseBuilder()
                .setGeneralStatus(GeneralStatus.NOT_FOUND)
                .build();
    }

    /**
     * Method to handle rest exception.
     *
     * @param ex {@link RestException}
     * @return {@link GeneralResponse} Response body of internal server error.
     */
    @ExceptionHandler(RestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GeneralResponse handleRuntimeException(RestException ex) {
        logger.error("HTTP message error! " + ex.getGeneralStatus(), ex);
        return getResponseBuilder()
                .setGeneralStatus(ex.getGeneralStatus())
                .build();
    }

    /**
     * Method to handle runtime exception.
     *
     * @param ex {@link RuntimeException}
     * @return {@link GeneralResponse} Response body of internal server error.
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GeneralResponse handleRuntimeException(RuntimeException ex) {
        logger.error("HTTP message error! " + GeneralStatus.INTERNAL_SERVER_ERROR, ex);
        return getResponseBuilder()
                .setGeneralStatus(GeneralStatus.INTERNAL_SERVER_ERROR)
                .build();
    }
}
