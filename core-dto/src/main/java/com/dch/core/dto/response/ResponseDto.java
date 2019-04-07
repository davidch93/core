package com.dch.core.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Date;

/**
 * Class that defined response message from server and the return type should be
 * written straight to the HTTP response body.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@JsonInclude(Include.NON_NULL)
public class ResponseDto implements GeneralResponse {

    private Date timestamp;
    private Object data;
    private ResponseStatusDto status;

    public ResponseDto() {
    }

    public ResponseDto(Date timestamp, Object data, ResponseStatusDto status) {
        this.timestamp = timestamp;
        this.data = data;
        this.status = status;
    }

    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @return the status
     */
    public ResponseStatusDto getStatus() {
        return status;
    }

    /**
     * @param status the status to set
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
    public static class ResponseStatusDto {

        private String code;
        private String type;
        private String message;

        public ResponseStatusDto() {
        }

        public ResponseStatusDto(String code, String type, String message) {
            this.code = code;
            this.type = type;
            this.message = message;
        }

        /**
         * @return the code
         */
        public String getCode() {
            return code;
        }

        /**
         * @param code the code to set
         */
        public void setCode(String code) {
            this.code = code;
        }

        /**
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * @param type the type to set
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * @return the message
         */
        public String getMessage() {
            return message;
        }

        /**
         * @param message the message to set
         */
        public void setMessage(String message) {
            this.message = message;
        }
    }
}
