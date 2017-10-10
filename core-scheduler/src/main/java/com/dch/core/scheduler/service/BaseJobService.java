package com.dch.core.scheduler.service;

import com.dch.core.scheduler.item.SchedulerItem;

/**
 * Service used to do the job scheduler. Extends this interface if you want to
 * create job for scheduler.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jun 17, 2017
 */
public interface BaseJobService {

	/**
	 * Method used to do the job scheduler.
	 * 
	 * @param schedulerItem
	 *            {@link SchedulerItem}
	 */
	void doJob(SchedulerItem<?> schedulerItem);

	/**
	 * Method used to running the job.
	 * 
	 * @param schedulerItem
	 *            {@link SchedulerItem}
	 * @throws Exception
	 */
	void run(SchedulerItem<?> schedulerItem) throws Exception;
}
