package com.dch.core.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

/**
 * Class that defined response message from server and the return type should be
 * written straight to the HTTP response body.
 *
 * @param <T> the type of object data.
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@JsonInclude(Include.NON_NULL)
@Schema(description = "Defined base response message from the endpoint")
public class ResponseDto<T> implements GeneralResponse {

    @Schema(description = "Time when the endpoint was giving the response")
    private Date timestamp;

    @Schema(description = "Body of the data from the endpoint")
    private T data;

    @Schema(description = "Response status from the endpoint")
    private ResponseStatusDto status;

    /**
     * Instantiates a new Response DTO.
     */
    public ResponseDto() {
    }

    /**
     * Instantiates a new Response DTO.
     *
     * @param timestamp the timestamp
     * @param data      the data
     * @param status    the status
     */
    public ResponseDto(Date timestamp, T data, ResponseStatusDto status) {
        this.timestamp = timestamp;
        this.data = data;
        this.status = status;
    }

    /**
     * Get timestamp.
     *
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Set timestamp.
     *
     * @param timestamp the timestamp
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Get data.
     *
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * Set data.
     *
     * @param data the data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Get status.
     *
     * @return the status
     */
    public ResponseStatusDto getStatus() {
        return status;
    }

    /**
     * Set status.
     *
     * @param status the status
     */
    public void setStatus(ResponseStatusDto status) {
        this.status = status;
    }

    /**
     * Class that define response status from service.
     *
     * @author David.Christianto
     * @version 2.0.0
     * @since 1.0.0
     */
    @Schema(description = "Defined response status from the endpoint")
    public static class ResponseStatusDto {

        @Schema(description = "Response status code")
        private String code;

        @Schema(description = "Response status parameter")
        private String type;

        @Schema(description = "Response status message")
        private String message;

        /**
         * Instantiates a new Response Status DTO.
         */
        public ResponseStatusDto() {
        }

        /**
         * Instantiates a new Response Status DTO.
         *
         * @param code    the code
         * @param type    the type
         * @param message the message
         */
        public ResponseStatusDto(String code, String type, String message) {
            this.code = code;
            this.type = type;
            this.message = message;
        }

        /**
         * Get code.
         *
         * @return the code
         */
        public String getCode() {
            return code;
        }

        /**
         * Set code.
         *
         * @param code the code
         */
        public void setCode(String code) {
            this.code = code;
        }

        /**
         * Get type.
         *
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * Set type.
         *
         * @param type the type
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * Get message.
         *
         * @return the message
         */
        public String getMessage() {
            return message;
        }

        /**
         * Set message.
         *
         * @param message the message
         */
        public void setMessage(String message) {
            this.message = message;
        }
    }
}
