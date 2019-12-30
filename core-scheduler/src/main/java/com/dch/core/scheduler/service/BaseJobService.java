package com.dch.core.scheduler.service;

import com.dch.core.scheduler.item.SchedulerItem;

/**
 * Service used to do the job scheduler. Extends this interface if you want to
 * create job for scheduler.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public interface BaseJobService {

    /**
     * Do the job scheduler.
     *
     * @param schedulerItem the {@link SchedulerItem}
     */
    void doJob(SchedulerItem<?> schedulerItem);

    /**
     * Run the job.
     *
     * @param schedulerItem the {@link SchedulerItem}
     * @throws Exception If error occurred while run the job.
     */
    void run(SchedulerItem<?> schedulerItem) throws Exception;
}
