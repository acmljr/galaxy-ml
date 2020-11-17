package com.alopes.galaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.alopes.galaxy.services.SchedulerService;
import lombok.val;

@SpringBootApplication
public class GalaxyMlApplication {

    public static void main(final String[] args) {
        val context = SpringApplication.run(GalaxyMlApplication.class, args);

        val schedulerService = context.getBean(SchedulerService.class);

        schedulerService.triggerSummaryJobs();
    }

}
