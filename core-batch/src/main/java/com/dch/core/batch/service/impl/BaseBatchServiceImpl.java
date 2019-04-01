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
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common batch methods that they might all use. You should only need to extend
 * this class when your require custom batch service. This class implements
 * {@link BaseBatchService}.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jun 18, 2017
 * @since 1.0.0-SNAPSHOT
 */
@ComponentScan("com.dch.core.batch")
public abstract class BaseBatchServiceImpl implements BaseBatchService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseBatchServiceImpl.class);

    @Autowired
    protected JobLauncher jobLauncher;

    @Autowired
    protected JobBuilderFactory jobBuilderFactory;

    @Autowired
    protected StepBuilderFactory stepBuilderFactory;

    @Autowired
    protected BatchSetting batchSetting;

    @Override
    public void execute(String jobName, String stepName, JobParameters jobParameters) {
        try {
            jobLauncher.run(getJob(jobName, stepName, jobParameters), jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException ex) {
            LOGGER.error(String.format("[%s] %s", batchSetting.getIdentityPrefix(), ex.getMessage()), ex);
            throw new BatchException("Error occurred when execute batch job!", ex);
        }
    }

    @Override
    public Job getJob(String jobName, String stepName, JobParameters jobParameters) {
        // @formatter:off
		return jobBuilderFactory
			.get(jobName)
				.incrementer(new RunIdIncrementer())
				.listener(getListener(jobParameters))
				.flow(getStep(stepName, jobParameters))
				.end()
			.build();
		// @formatter:on
    }

    @SuppressWarnings("unchecked")
    @Override
    public Step getStep(String stepName, JobParameters jobParameters) {
        // @formatter:off
		return stepBuilderFactory
			.get(stepName)
				.chunk(batchSetting.getChunkSize())
				.reader(getReader(jobParameters))
				.processor((ItemProcessor<? super Object, ? extends Object>) getProcessor(jobParameters))
				.writer((ItemWriter<? super Object>) getWriter(jobParameters))
			.build();
		// @formatter:on
    }
}
