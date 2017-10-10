package com.dch.core.scheduler.task;

import org.springframework.context.ApplicationContext;

import com.dch.core.scheduler.item.SchedulerItem;
import com.dch.core.scheduler.service.BaseJobService;
import com.dch.core.scheduler.service.BaseSchedulerService;

/**
 * Classes used to processes schedule based on {@link SchedulerItem}. This class
 * implements {@link Runnable}.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jun 17, 2017
 */
public class TaskRunnable implements Runnable {

	private ApplicationContext applicationContext;
	private BaseSchedulerService schedulerService;
	private SchedulerItem<?> schedulerItem;

	public TaskRunnable(ApplicationContext applicationContext, BaseSchedulerService schedulerService,
			SchedulerItem<?> schedulerItem) {
		this.applicationContext = applicationContext;
		this.schedulerService = schedulerService;
		this.schedulerItem = schedulerItem;
	}

	@Override
	public void run() {
		if (schedulerItem != null) {
			try {
				BaseJobService jobService = applicationContext.getBean(schedulerItem.getServiceName(),
						BaseJobService.class);
				jobService.doJob(schedulerItem);
			} catch (Exception ex) {
				schedulerService.beforeSchedule(schedulerItem);
				schedulerService.afterSchedule(schedulerItem, "Service not found: " + schedulerItem.getServiceName());
			}
		}
	}
}
