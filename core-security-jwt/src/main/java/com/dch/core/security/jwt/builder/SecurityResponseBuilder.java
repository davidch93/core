package com.dch.core.security.jwt.builder;

import com.dch.core.datastatic.GenericStatus;
import com.dch.core.datastatic.builder.ResponseBuilderHelper;
import com.dch.core.datastatic.response.GenericResponse;
import com.dch.core.dto.response.ResponseDto;
import com.dch.core.dto.response.ResponseDto.ResponseStatusDto;
import org.springframework.context.MessageSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Class builder used to create {@link GenericResponse} security. This class
 * extends {@link ResponseBuilderHelper}.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jul 24, 2017
 * @since 1.0.0-SNAPSHOT
 */
public class SecurityResponseBuilder extends ResponseBuilderHelper {

    private Date timestamp;
    private Object data;
    private GenericStatus genericStatus;
    private Object[] args;
    private MessageSource messageSource;

    public SecurityResponseBuilder(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public SecurityResponseBuilder setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * @param data the data to set
     */
    @Override
    public SecurityResponseBuilder setData(Object data) {
        this.data = data;
        return this;
    }

    /**
     * @param genericStatus the genericStatus to set
     */
    @Override
    public SecurityResponseBuilder setGenericStatus(GenericStatus genericStatus) {
        this.genericStatus = genericStatus;
        return this;
    }

    /**
     * @param args the args to set
     */
    @Override
    public SecurityResponseBuilder setArgs(Object[] args) {
        this.args = args;
        return this;
    }

    @Override
    public GenericResponse build() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        return new ResponseDto(timestamp == null ? new Date() : timestamp, data, new ResponseStatusDto(
                genericStatus.getCode(), genericStatus.getParameter(),
                messageSource.getMessage(genericStatus.getValue(), args, RequestContextUtils.getLocale(request))));
    }
}
