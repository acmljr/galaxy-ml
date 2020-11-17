package com.alopes.galaxy.configurations;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Simple configuration to start the scheduler and specify where to adjust the scheduler
 * configurations.
 */
@Configuration
public class QuartzConfiguration {


    @Bean
    public Scheduler scheduler(final SchedulerFactoryBean factory) throws SchedulerException {

        final Scheduler scheduler = factory.getScheduler();

        scheduler.start();

        return scheduler;
    }

}
