package com.dch.core.scheduler.service.impl;

import com.dch.core.scheduler.config.SchedulerSetting;
import com.dch.core.scheduler.exception.SchedulerException;
import com.dch.core.scheduler.item.SchedulerItem;
import com.dch.core.scheduler.service.BaseSchedulerService;
import com.dch.core.scheduler.task.TaskRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * extend this class when your require custom scheduler service. This class
 * implements {@link BaseSchedulerService}.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jun 17, 2017
 * @since 1.0.0-SNAPSHOT
 */
@ComponentScan("com.dch.core.scheduler")
public abstract class BaseSchedulerServiceImpl implements BaseSchedulerService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseSchedulerServiceImpl.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    protected ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    protected SchedulerSetting schedulerSetting;

    protected Map<Integer, ScheduledFuture<?>> scheduleFeatures = new HashMap<>();

    @Override
    public void schedule(SchedulerItem<?> schedulerItem) {
        try {
            ScheduledFuture<?> scheduledFuture = taskScheduler
                    .schedule(new TaskRunnable(applicationContext, this, schedulerItem), getTrigger(schedulerItem));
            scheduleFeatures.put(schedulerItem.getSchedulerId(), scheduledFuture);
        } catch (TaskRejectedException ex) {
            LOGGER.error(String.format("[%s] %s", schedulerSetting.getIdentityPrefix(), ex.getMessage()), ex);
            throw new SchedulerException(ex.getMessage(), ex);
        }
    }

    @Override
    public void scheduleAll(List<SchedulerItem<?>> schedulerItems) {
        schedulerItems.forEach(schedulerItem -> schedule(schedulerItem));
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
