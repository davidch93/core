package com.dch.core.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.dch.core.datastatic.GenericStatus;
import com.dch.core.datastatic.response.GenericResponse;

/**
 * Class that define response message from service and represents the HTTP
 * response body.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Apr 23, 2017
 */
@JsonInclude(Include.NON_NULL)
public class ResponseServiceDto<T> implements GenericResponse {

	private GenericStatus status;
	private T message;
	private List<T> messageList;
	private String error;

	public ResponseServiceDto() {
	}

	public ResponseServiceDto(GenericStatus status, T message) {
		this.status = status;
		this.message = message;
	}

	public ResponseServiceDto(GenericStatus status, List<T> messageList) {
		this.status = status;
		this.messageList = messageList;
	}

	public ResponseServiceDto(GenericStatus status, String error) {
		this.status = status;
		this.error = error;
	}

	/**
	 * @return the status
	 */
	public GenericStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
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
	 * @param message
	 *            the message to set
	 */
	public void setMessage(T message) {
		this.message = message;
	}

	/**
	 * @return the messageList
	 */
	public List<T> getMessageList() {
		return messageList;
	}

	/**
	 * @param messageList
	 *            the messageList to set
	 */
	public void setMessageList(List<T> messageList) {
		this.messageList = messageList;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
}
