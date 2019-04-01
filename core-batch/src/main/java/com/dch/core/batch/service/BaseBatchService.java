package com.dch.core.batch.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;

/**
 * Generic Manager used to provide common batch methods. Extend this interface
 * if you want typesafe (no casting necessary) managers for your batch.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jun 18, 2017
 * @since 1.0.0-SNAPSHOT
 */
public interface BaseBatchService {

    /**
     * Method used to execute batch service.
     *
     * @param jobName       {@link String} Job name.
     * @param stepName      {@link String} Step name.
     * @param jobParameters {@link JobParameters} Parameters.
     */
    void execute(String jobName, String stepName, JobParameters jobParameters);

    /**
     * Method used to get Job.
     *
     * @param jobName       {@link String} Job name.
     * @param stepName      {@link String} Step name.
     * @param jobParameters {@link JobParameters} Parameters.
     * @return {@link Job}
     */
    Job getJob(String jobName, String stepName, JobParameters jobParameters);

    /**
     * Method used to get Step.
     *
     * @param stepName      {@link String} Step name.
     * @param jobParameters {@link JobParameters} Parameters.
     * @return {@link Step}
     */
    Step getStep(String stepName, JobParameters jobParameters);

    /**
     * Method used to get Listener.
     *
     * @param jobParameters {@link JobParameters} Parameters.
     * @return {@link JobExecutionListenerSupport}
     */
    JobExecutionListenerSupport getListener(JobParameters jobParameters);

    /***
     * Method used to get Reader.
     *
     * @param jobParameters
     *            {@link JobParameters} Parameters.
     * @return {@link ItemReader}
     */
    ItemReader<?> getReader(JobParameters jobParameters);

    /**
     * Method used to get Processor.
     *
     * @param jobParameters {@link JobParameters} Parameters.
     * @return {@link ItemProcessor}
     */
    ItemProcessor<?, ?> getProcessor(JobParameters jobParameters);

    /**
     * Method used to get Writer.
     *
     * @param jobParameters {@link JobParameters} Parameters.
     * @return {@link ItemWriter}
     */
    ItemWriter<?> getWriter(JobParameters jobParameters);
}
