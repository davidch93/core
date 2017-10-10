package com.dch.core.scheduler.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dch.core.scheduler.config.SchedulerSetting;
import com.dch.core.scheduler.item.SchedulerItem;
import com.dch.core.scheduler.service.BaseJobService;
import com.dch.core.scheduler.service.BaseSchedulerService;

/**
 * This class serves as the Base class for all other Managers - namely to do
 * common job methods that they might all use. You should only need to extend
 * this class when your require custom job service. This class implements
 * {@link BaseJobService}.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jun 17, 2017
 */
public abstract class BaseJobServiceImpl implements BaseJobService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(BaseJobServiceImpl.class);

	@Autowired
	protected SchedulerSetting schedulerSetting;

	@Autowired
	protected BaseSchedulerService schedulerService;

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
