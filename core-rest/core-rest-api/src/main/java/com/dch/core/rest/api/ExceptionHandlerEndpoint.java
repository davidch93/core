package com.dch.core.rest.api;

import com.dch.core.datastatic.GeneralStatus;
import com.dch.core.dto.response.ResponseDto;
import com.dch.core.rest.api.exception.RestException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Class that defined exception handler for all endpoints.
 *
 * @author david.christianto
 * @version 2.0.0
 * @see BaseEndpoint
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
     * @param ex the {@link HttpMessageNotReadableException}
     * @return {@link ResponseDto Response body} of message not readable.
     */
    @ApiResponse(responseCode = "400", description = "HTTP message not readable because of malformed JSON request",
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseDto.class)))
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<Void> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        logger.error("HTTP message error! " + GeneralStatus.MESSAGE_NOT_READABLE, ex);
        return getResponseBuilder(Void.class)
                .setGeneralStatus(GeneralStatus.MESSAGE_NOT_READABLE)
                .build();
    }

    /**
     * Method to handle response method argument not valid exception.
     *
     * @param ex the {@link MethodArgumentNotValidException}
     * @return {@link ResponseDto Response body} of validation error.
     */
    @ApiResponse(responseCode = "400", description = "Method argument not valid because of failed to validate JSON request",
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseDto.class)))
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<Map> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.error("HTTP message error! " + GeneralStatus.ARGUMENT_NOT_VALID, ex);

        Map<String, String> errorsMap = ex.getBindingResult().getAllErrors().stream()
                .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(),
                        objectError -> messageSource.getMessage(Objects.requireNonNull(objectError.getDefaultMessage()),
                                objectError.getArguments(), LocaleContextHolder.getLocale()),
                        (objectError1, objectError2) -> objectError2));

        return getResponseBuilder(Map.class)
                .setData(errorsMap)
                .setGeneralStatus(GeneralStatus.ARGUMENT_NOT_VALID)
                .build();
    }

    /**
     * Method to handle rest exception.
     *
     * @param ex the {@link RestException}
     * @return {@link ResponseDto Response body} of internal server error.
     */
    @ApiResponse(responseCode = "400", description = "The server encountered a client side error",
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseDto.class)))
    @ExceptionHandler(RestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<Void> handleRuntimeException(RestException ex) {
        logger.error("HTTP message error! " + ex.getGeneralStatus(), ex);
        return getResponseBuilder(Void.class)
                .setGeneralStatus(ex.getGeneralStatus())
                .setArgs(ex.getArgs())
                .build();
    }

    /**
     * Method to handle runtime exception.
     *
     * @param ex the {@link RuntimeException}
     * @return {@link ResponseDto Response body} of internal server error.
     */
    @ApiResponse(responseCode = "500", description = "The server encountered an internal error",
            content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseDto.class)))
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto<Void> handleRuntimeException(RuntimeException ex) {
        logger.error("HTTP message error! " + GeneralStatus.INTERNAL_SERVER_ERROR, ex);
        return getResponseBuilder(Void.class)
                .setGeneralStatus(GeneralStatus.INTERNAL_SERVER_ERROR)
                .build();
    }
}
