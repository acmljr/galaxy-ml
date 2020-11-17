package com.alopes.galaxy.services;

import static utils.Constants.DAYS_BATCH;
import static utils.Constants.DAYS_EACH_YEAR;
import static utils.Constants.YEARS;
import java.util.UUID;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alopes.galaxy.jobs.ForecastJob;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SchedulerService {

    @Autowired
    private Scheduler scheduler;

    public void triggerSummaryJobs() {

        val totalDays = YEARS * DAYS_EACH_YEAR;


        for (Integer firstDayJob = 0; firstDayJob < totalDays; firstDayJob += DAYS_BATCH) {
            val jobDetail = buildJobDetail(firstDayJob);
            val trigger = buildTrigger(jobDetail);
            try {
                scheduler.scheduleJob(jobDetail, trigger);
            } catch (final SchedulerException e) {
                log.error("Couldn't schedule jobs");
                e.printStackTrace();
            }
        }
        log.info("All jobs scheduled.");

    }

    private Trigger buildTrigger(final JobDetail job) {
        return TriggerBuilder.newTrigger().forJob(job).withIdentity(job.getKey() + "-trigger")
                .withDescription("Sample trigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()).startNow().build();
    }

    private JobDetail buildJobDetail(final Integer day) {

        final JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("firstDay", day.toString());

        return JobBuilder.newJob(ForecastJob.class)
                .withIdentity(UUID.randomUUID().toString(), "forecast-jobs")
                .withDescription("Send Email Job").usingJobData(jobDataMap).storeDurably().build();
    }

}
