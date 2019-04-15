package com.dch.core.batch.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * A convenience batch configurer that used to configure default
 * {@link org.springframework.batch.core.launch.JobLauncher},
 * {@link org.springframework.batch.core.configuration.annotation.JobBuilderFactory},
 * {@link org.springframework.batch.core.configuration.annotation.StepBuilderFactory}
 * configuration.
 *
 * @author david.christianto
 * @version 2.0.0
 * @see org.springframework.batch.core.launch.support.SimpleJobLauncher
 */
@ComponentScan("com.dch.core.batch")
@EnableConfigurationProperties(BatchSetting.class)
public class BatchConfigurerSupport {

}
