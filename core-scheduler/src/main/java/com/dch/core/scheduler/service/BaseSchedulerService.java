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
     * Method used to schedule job.
     *
     * @param schedulerItem {@link SchedulerItem}
     */
    void schedule(SchedulerItem<?> schedulerItem);

    /**
     * Method used to schedule all jobs.
     *
     * @param schedulerItems {@link List} of {@link SchedulerItem}
     */
    void scheduleAll(List<SchedulerItem<?>> schedulerItems);

    /**
     * Method used to stop schedule by ID.
     *
     * @param schedulerId {@link Integer} Scheduler ID.
     */
    void stopSchedule(Integer schedulerId);

    /**
     * Method used to stop all schedules.
     */
    void stopAllSchedules();

    /**
     * Method used to execute before start schedule.
     *
     * @param schedulerItem {@link SchedulerItem}
     */
    void beforeSchedule(SchedulerItem<?> schedulerItem);

    /**
     * Method used to execute after schedule.
     *
     * @param schedulerItem {@link SchedulerItem}
     */
    void afterSchedule(SchedulerItem<?> schedulerItem);

    /**
     * Method used to execute after schedule with error message.
     *
     * @param schedulerItem {@link SchedulerItem}
     * @param errorMessage  {@link String}
     */
    void afterSchedule(SchedulerItem<?> schedulerItem, String errorMessage);

    /**
     * Method used to get trigger by scheduler item.
     *
     * @param schedulerItem {@link SchedulerItem}
     * @return {@link Trigger}
     */
    Trigger getTrigger(SchedulerItem<?> schedulerItem);
}
