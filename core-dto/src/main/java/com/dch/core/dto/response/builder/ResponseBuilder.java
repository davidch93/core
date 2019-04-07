package com.dch.core.dto.response.builder;

import com.dch.core.datastatic.GeneralStatus;
import com.dch.core.datastatic.builder.GeneralBuilder;
import com.dch.core.dto.response.GeneralResponse;
import com.dch.core.dto.response.ResponseDto;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Date;

/**
 * Class builder used to create {@link GeneralResponse} to provide information
 * about general HTTP response.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see com.dch.core.datastatic.builder.GeneralBuilder
 * @since 1.0.0
 */
public class ResponseBuilder implements GeneralBuilder<GeneralResponse> {

    private final MessageSource messageSource;
    private Date timestamp;
    private Object data;
    private GeneralStatus generalStatus;
    private Object[] args;

    public ResponseBuilder(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Method used to build timestamp.
     *
     * @param timestamp the timestamp to set
     * @return {@link ResponseBuilder} Builder helper.
     */
    public ResponseBuilder setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * Method used to build data.
     *
     * @param data {@code Object} Content of data.
     * @return {@link ResponseBuilder} Builder helper.
     */
    public ResponseBuilder setData(Object data) {
        this.data = data;
        return this;
    }

    /**
     * Method used to build generic status.
     *
     * @param generalStatus {@link GeneralStatus}
     * @return {@link ResponseBuilder} Builder helper.
     */
    public ResponseBuilder setGeneralStatus(GeneralStatus generalStatus) {
        this.generalStatus = generalStatus;
        return this;
    }

    /**
     * Method used to build arguments.
     *
     * @param args {@code Object[]} Additional information.
     * @return {@link ResponseBuilder} Builder helper.
     */
    public ResponseBuilder setArgs(Object[] args) {
        this.args = args;
        return this;
    }

    @Override
    public GeneralResponse build() {
        return new ResponseDto(timestamp == null ? new Date() : timestamp, data, new ResponseDto.ResponseStatusDto(
                generalStatus.getCode(), generalStatus.getParameter(),
                messageSource.getMessage(generalStatus.getValue(), args, LocaleContextHolder.getLocale())));
    }
}
