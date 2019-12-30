package com.dch.core.scheduler.service;

import com.dch.core.scheduler.item.SchedulerItem;
import org.springframework.scheduling.Trigger;

import java.util.List;

/**
 * Generic Manager used to provide common scheduler methods. Extend this
 * interface if you want typesafe (no casting necessary) managers for your
 * scheduler.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public interface BaseSchedulerService {

    /**
     * Schedule a job.
     *
     * @param schedulerItem the {@link SchedulerItem}
     */
    void schedule(SchedulerItem<?> schedulerItem);

    /**
     * Schedule all jobs.
     *
     * @param schedulerItems list of {@link SchedulerItem}
     */
    void scheduleAll(List<SchedulerItem<?>> schedulerItems);

    /**
     * Stop schedule by ID.
     *
     * @param schedulerId the Scheduler ID.
     */
    void stopSchedule(Integer schedulerId);

    /**
     * Stop all schedules.
     */
    void stopAllSchedules();

    /**
     * Execute before start schedule.
     *
     * @param schedulerItem the {@link SchedulerItem}
     */
    void beforeSchedule(SchedulerItem<?> schedulerItem);

    /**
     * Execute after schedule.
     *
     * @param schedulerItem the {@link SchedulerItem}
     */
    void afterSchedule(SchedulerItem<?> schedulerItem);

    /**
     * Execute after schedule with error message.
     *
     * @param schedulerItem the {@link SchedulerItem}
     * @param errorMessage  the error message.
     */
    void afterSchedule(SchedulerItem<?> schedulerItem, String errorMessage);

    /**
     * Get trigger by scheduler item.
     *
     * @param schedulerItem the {@link SchedulerItem}
     * @return the {@link Trigger}
     */
    Trigger getTrigger(SchedulerItem<?> schedulerItem);
}
