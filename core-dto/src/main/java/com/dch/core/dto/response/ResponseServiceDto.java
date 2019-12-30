package com.dch.core.dto.response;

import com.dch.core.datastatic.GeneralStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Class that define response message from service and represents the HTTP
 * response body.
 *
 * @param <T> the type parameter
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@JsonInclude(Include.NON_NULL)
@Schema(description = "Defined base response message from the service.")
public class ResponseServiceDto<T> implements GeneralResponse {

    @Schema(description = "Response status from the service")
    private GeneralStatus status;

    @Schema(description = "Body of data")
    private T message;

    @Schema(description = "List of data")
    private ContentListDto<T> messageList;

    /**
     * Instantiates a new Response Service DTO.
     */
    public ResponseServiceDto() {
    }

    /**
     * Instantiates a new Response Service DTO.
     *
     * @param status  the status
     * @param message the message
     */
    public ResponseServiceDto(GeneralStatus status, T message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Instantiates a new Response Service DTO.
     *
     * @param status      the status
     * @param messageList the message list
     */
    public ResponseServiceDto(GeneralStatus status, ContentListDto<T> messageList) {
        this.status = status;
        this.messageList = messageList;
    }

    /**
     * Get status.
     *
     * @return the status
     */
    public GeneralStatus getStatus() {
        return status;
    }

    /**
     * Set status.
     *
     * @param status the status
     */
    public void setStatus(GeneralStatus status) {
        this.status = status;
    }

    /**
     * Get message.
     *
     * @return the message
     */
    public T getMessage() {
        return message;
    }

    /**
     * Set message.
     *
     * @param message the message
     */
    public void setMessage(T message) {
        this.message = message;
    }

    /**
     * Get message list.
     *
     * @return the message list
     */
    public ContentListDto<T> getMessageList() {
        return messageList;
    }

    /**
     * Set message list.
     *
     * @param messageList the message list
     */
    public void setMessageList(ContentListDto<T> messageList) {
        this.messageList = messageList;
    }
}
