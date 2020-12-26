package com.dch.core.batch.service.impl;

import com.dch.core.batch.config.BatchSetting;
import com.dch.core.batch.exception.BatchException;
import com.dch.core.batch.service.BaseBatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.ComponentScan;

/**
 * This class serves as the Base class for all other Batch Service - namely to hold
 * common batch methods that they might all use. You should only need to extend
 * this class when your require custom batch service.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see BaseBatchService
 * @since 1.0.0
 */
@ComponentScan("com.dch.core.batch")
public abstract class BaseBatchServiceImpl implements BaseBatchService {

    protected static final Logger logger = LoggerFactory.getLogger(BaseBatchServiceImpl.class);

    protected final JobLauncher jobLauncher;
    protected final JobBuilderFactory jobBuilderFactory;
    protected final StepBuilderFactory stepBuilderFactory;
    protected final BatchSetting batchSetting;

    protected BaseBatchServiceImpl(JobLauncher jobLauncher, JobBuilderFactory jobBuilderFactory,
                                   StepBuilderFactory stepBuilderFactory, BatchSetting batchSetting) {
        this.jobLauncher = jobLauncher;
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.batchSetting = batchSetting;
    }

    @Override
    public void execute(String jobName, String stepName, JobParameters jobParameters) {
        try {
            jobLauncher.run(getJob(jobName, stepName, jobParameters), jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException ex) {
            logger.error("[{}] Error occurred when execute batch job!", batchSetting.getIdentityPrefix(), ex);
            throw new BatchException("Error occurred when execute batch job!", ex);
        }
    }

    @Override
    public Job getJob(String jobName, String stepName, JobParameters jobParameters) {
        return jobBuilderFactory.get(jobName)
                .incrementer(new RunIdIncrementer())
                .listener(getListener(jobParameters))
                .flow(getStep(stepName, jobParameters)).end()
                .build();
    }

    @Override
    public Step getStep(String stepName, JobParameters jobParameters) {
        return stepBuilderFactory.get(stepName).chunk(batchSetting.getChunkSize())
                .reader(getReader(jobParameters))
                .processor(getProcessor(jobParameters))
                .writer(getWriter(jobParameters))
                .build();
    }
}
