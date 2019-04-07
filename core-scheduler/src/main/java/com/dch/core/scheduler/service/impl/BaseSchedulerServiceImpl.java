package com.dch.core.scheduler.service.impl;

import com.dch.core.scheduler.config.SchedulerSetting;
import com.dch.core.scheduler.exception.SchedulerException;
import com.dch.core.scheduler.item.SchedulerItem;
import com.dch.core.scheduler.service.BaseSchedulerService;
import com.dch.core.scheduler.task.TaskRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common scheduler methods that they might all use. You should only need to
 * extend this class when your require custom scheduler service.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see com.dch.core.scheduler.service.BaseSchedulerService
 * @since 1.0.0
 */
@ComponentScan("com.dch.core.scheduler")
public abstract class BaseSchedulerServiceImpl implements BaseSchedulerService {

    protected static final Logger logger = LoggerFactory.getLogger(BaseSchedulerServiceImpl.class);

    private final ApplicationContext applicationContext;
    protected final ThreadPoolTaskScheduler taskScheduler;
    protected final SchedulerSetting schedulerSetting;
    protected Map<Integer, ScheduledFuture<?>> scheduleFeatures = new HashMap<>();

    protected BaseSchedulerServiceImpl(ApplicationContext applicationContext, ThreadPoolTaskScheduler taskScheduler,
                                       SchedulerSetting schedulerSetting) {
        this.applicationContext = applicationContext;
        this.taskScheduler = taskScheduler;
        this.schedulerSetting = schedulerSetting;
    }

    @Override
    public void schedule(SchedulerItem<?> schedulerItem) {
        try {
            ScheduledFuture<?> scheduledFuture = taskScheduler
                    .schedule(new TaskRunnable(applicationContext, this, schedulerItem), getTrigger(schedulerItem));
            scheduleFeatures.put(schedulerItem.getSchedulerId(), scheduledFuture);
        } catch (TaskRejectedException ex) {
            logger.error(String.format("[%s] %s", schedulerSetting.getIdentityPrefix(), ex.getMessage()), ex);
            throw new SchedulerException("Error occurred while scheduling job!", ex);
        }
    }

    @Override
    public void scheduleAll(List<SchedulerItem<?>> schedulerItems) {
        schedulerItems.forEach(this::schedule);
    }

    @Override
    public void stopSchedule(Integer schedulerId) {
        if (scheduleFeatures.containsKey(schedulerId))
            scheduleFeatures.get(schedulerId).cancel(false);
    }

    @Override
    public void stopAllSchedules() {
        scheduleFeatures.forEach((schedulerId, schedule) -> schedule.cancel(false));
    }
}
