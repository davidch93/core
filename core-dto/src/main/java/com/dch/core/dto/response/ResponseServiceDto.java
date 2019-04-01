package com.dch.core.dto.response;

import com.dch.core.datastatic.GenericStatus;
import com.dch.core.datastatic.response.GenericResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Class that define response message from service and represents the HTTP
 * response body.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Apr 23, 2017
 * @since 1.0.0-SNAPSHOT
 */
@JsonInclude(Include.NON_NULL)
public class ResponseServiceDto<T> implements GenericResponse {

    private GenericStatus status;
    private T message;
    private ContentListDto<T> messageList;

    public ResponseServiceDto() {
    }

    public ResponseServiceDto(GenericStatus status, T message) {
        this.status = status;
        this.message = message;
    }

    public ResponseServiceDto(GenericStatus status, ContentListDto<T> messageList) {
        this.status = status;
        this.messageList = messageList;
    }

    /**
     * @return the status
     */
    public GenericStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(GenericStatus status) {
        this.status = status;
    }

    /**
     * @return the message
     */
    public T getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(T message) {
        this.message = message;
    }

    /**
     * @return the messageList
     */
    public ContentListDto<T> getMessageList() {
        return messageList;
    }

    /**
     * @param messageList the messageList to set
     */
    public void setMessageList(ContentListDto<T> messageList) {
        this.messageList = messageList;
    }
}
