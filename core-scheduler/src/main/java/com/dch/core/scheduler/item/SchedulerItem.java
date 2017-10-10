package com.dch.core.scheduler.item;

/**
 * Classes used to provide job information to be scheduled on the scheduler and
 * other information about the scheduler data.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jun 17, 2017
 * @param <T>
 *            Entity.
 */
public class SchedulerItem<T> {

	private Integer schedulerId;
	private T item;
	private String serviceName;

	/**
	 * @return the schedulerId
	 */
	public Integer getSchedulerId() {
		return schedulerId;
	}

	/**
	 * @param schedulerId
	 *            the schedulerId to set
	 */
	public void setSchedulerId(Integer schedulerId) {
		this.schedulerId = schedulerId;
	}

	/**
	 * @return the item
	 */
	public T getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(T item) {
		this.item = item;
	}

	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @param serviceName
	 *            the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public String toString() {
		return "SchedulerItem [schedulerId=" + schedulerId + ", item=" + item + ", serviceName=" + serviceName + "]";
	}
}
