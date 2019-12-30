package com.dch.core.scheduler.item;

/**
 * Classes used to provide job information to be scheduled on the scheduler and
 * other information about the scheduler data.
 *
 * @param <T> Entity.
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public class SchedulerItem<T> {

    private Integer schedulerId;
    private T item;
    private String serviceName;

    /**
     * Get scheduler id.
     *
     * @return the scheduler id
     */
    public Integer getSchedulerId() {
        return schedulerId;
    }

    /**
     * Set scheduler id.
     *
     * @param schedulerId the scheduler id
     */
    public void setSchedulerId(Integer schedulerId) {
        this.schedulerId = schedulerId;
    }

    /**
     * Get item.
     *
     * @return the item
     */
    public T getItem() {
        return item;
    }

    /**
     * Set item.
     *
     * @param item the item
     */
    public void setItem(T item) {
        this.item = item;
    }

    /**
     * Get service name.
     *
     * @return the service name
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Set service name.
     *
     * @param serviceName the service name
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
