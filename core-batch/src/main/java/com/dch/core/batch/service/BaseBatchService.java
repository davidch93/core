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
 * @version 2.0.0
 * @since 1.0.0
 */
public interface BaseBatchService {

    /**
     * Execute batch service.
     *
     * @param jobName       the job name.
     * @param stepName      the step name.
     * @param jobParameters the {@link JobParameters job parameters}.
     */
    void execute(String jobName, String stepName, JobParameters jobParameters);

    /**
     * This is the main module, which consist of the business logic to be run.
     *
     * @param jobName       the job name.
     * @param stepName      the step name.
     * @param jobParameters the {@link JobParameters job parameters}.
     * @return the {@link Job main job}
     */
    Job getJob(String jobName, String stepName, JobParameters jobParameters);

    /**
     * Steps are nothing but an execution flow of the job. A complex job can be divided into several steps or chunks,
     * which can be run one after another or ran depending on the result of the previous steps.
     *
     * @param stepName      the step name.
     * @param jobParameters the {@link JobParameters job parameters}.
     * @return the {@link Step specific step}
     */
    Step getStep(String stepName, JobParameters jobParameters);

    /**
     * Get default {@code JobExecutionListenerSupport}.
     *
     * @param jobParameters the {@link JobParameters job parameters}.
     * @return the {@link JobExecutionListenerSupport}
     */
    default JobExecutionListenerSupport getListener(JobParameters jobParameters) {
        return new JobExecutionListenerSupport();
    }

    /**
     * This interface is used to perform bulk-reading of data, e.g. reading several lines of data from an Excel file
     * when a job starts.
     *
     * @param jobParameters the {@link JobParameters job parameters}.
     * @return the {@link ItemReader item reader}
     */
    default ItemReader<? super Object> getReader(JobParameters jobParameters) {
        return null;
    }

    /**
     * When the data is read using {@code ItemReader}, {@code ItemProcessor} can be used to perform the processing of
     * data, depending on the business logic.
     *
     * @param jobParameters the {@link JobParameters job parameters}.
     * @return the {@link ItemProcessor item processor}
     */
    default ItemProcessor<? super Object, ?> getProcessor(JobParameters jobParameters) {
        return null;
    }

    /**
     * This interface is used to write bulk data — either to a database or any other file disks.
     *
     * @param jobParameters the {@link JobParameters job parameters}.
     * @return the {@link ItemWriter item writer}
     */
    default ItemWriter<? super Object> getWriter(JobParameters jobParameters) {
        return null;
    }
}
