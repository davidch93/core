package com.dch.core.scheduler.service.impl;

import com.dch.core.scheduler.config.SchedulerSetting;
import com.dch.core.scheduler.item.SchedulerItem;
import com.dch.core.scheduler.service.BaseJobService;
import com.dch.core.scheduler.service.BaseSchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class serves as the Base class for all other Managers - namely to do
 * common job methods that they might all use. You should only need to extend
 * this class when your require custom job service.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see com.dch.core.scheduler.service.BaseJobService
 * @since 1.0.0
 */
public abstract class BaseJobServiceImpl implements BaseJobService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseJobServiceImpl.class);

    protected final SchedulerSetting schedulerSetting;
    protected final BaseSchedulerService schedulerService;

    protected BaseJobServiceImpl(SchedulerSetting schedulerSetting, BaseSchedulerService schedulerService) {
        this.schedulerSetting = schedulerSetting;
        this.schedulerService = schedulerService;
    }

    @Override
    public void doJob(SchedulerItem<?> schedulerItem) {
        try {
            schedulerService.beforeSchedule(schedulerItem);
            run(schedulerItem);
            schedulerService.afterSchedule(schedulerItem);
        } catch (Exception ex) {
            LOGGER.error(String.format("[%s] %s", schedulerSetting.getIdentityPrefix(), ex.getMessage()), ex);
            schedulerService.afterSchedule(schedulerItem, ex.getMessage());
        }
    }
}
