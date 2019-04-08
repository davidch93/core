package com.dch.core.rest.reactive;

import com.dch.core.datastatic.GeneralStatus;
import com.dch.core.dto.response.ResponseDto;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.NotAcceptableStatusException;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;

import java.util.Map;

/**
 * Configuration class that extends {@link DefaultErrorAttributes} to override
 * {@link DefaultErrorAttributes#getErrorAttributes(ServerRequest, boolean)} and
 * create custom global error response attributes.
 *
 * @author david.christianto
 * @version 2.0.0
 * @see org.springframework.boot.web.reactive.error.DefaultErrorAttributes
 */
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    private final MessageSource messageSource;

    public GlobalErrorAttributes(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, includeStackTrace);
        errorAttributes.put("responseStatus", getResponseStatus(request));
        return errorAttributes;
    }

    /**
     * Method used to generate additional value to HTTP response body.
     *
     * @param request {@link ServerRequest}
     * @return {@link ResponseDto.ResponseStatusDto}
     */
    private ResponseDto.ResponseStatusDto getResponseStatus(ServerRequest request) {
        Throwable error = super.getError(request);
        if (error instanceof MethodNotAllowedException)
            return getResponseStatusDto(GeneralStatus.METHOD_NOT_ALLOWED,
                    ((MethodNotAllowedException) error).getHttpMethod());
        else if (error instanceof NotAcceptableStatusException)
            return getResponseStatusDto(GeneralStatus.NOT_ACCEPTABLE);
        else if (error instanceof ServerWebInputException)
            return getResponseStatusDto(GeneralStatus.MESSAGE_NOT_READABLE);
        else if (error instanceof UnsupportedMediaTypeStatusException)
            return getResponseStatusDto(GeneralStatus.UNSUPPORTED_MEDIA_TYPE);
        return getResponseStatusDto(GeneralStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Method used to create {@link ResponseDto.ResponseStatusDto} as standard response.
     *
     * @param status {@link GeneralStatus} Response status.
     * @param args   Arguments.
     * @return {@link ResponseDto.ResponseStatusDto}
     */
    private ResponseDto.ResponseStatusDto getResponseStatusDto(GeneralStatus status, Object... args) {
        return new ResponseDto.ResponseStatusDto(status.getCode(), status.getParameter(),
                messageSource.getMessage(status.getValue(), args, LocaleContextHolder.getLocale()));
    }
}
