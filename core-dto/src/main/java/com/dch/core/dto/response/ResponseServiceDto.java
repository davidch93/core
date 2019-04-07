package com.dch.core.dto.response;

import com.dch.core.datastatic.GeneralStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Class that define response message from service and represents the HTTP
 * response body.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@JsonInclude(Include.NON_NULL)
public class ResponseServiceDto<T> implements GeneralResponse {

    private GeneralStatus status;
    private T message;
    private ContentListDto<T> messageList;

    public ResponseServiceDto() {
    }

    public ResponseServiceDto(GeneralStatus status, T message) {
        this.status = status;
        this.message = message;
    }

    public ResponseServiceDto(GeneralStatus status, ContentListDto<T> messageList) {
        this.status = status;
        this.messageList = messageList;
    }

    /**
     * @return the status
     */
    public GeneralStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(GeneralStatus status) {
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
