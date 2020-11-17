package com.alopes.galaxy.jobs;

import static com.alopes.galaxy.services.WeatherForecaster.forecast;
import static utils.Constants.DAYS_BATCH;
import java.util.HashSet;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alopes.galaxy.models.Galaxy;
import com.alopes.galaxy.models.entities.WeatherForecast;
import com.alopes.galaxy.repositories.WeatherForecastRepository;
import lombok.val;
import lombok.extern.slf4j.Slf4j;
import utils.Constants;

/**
 * Simple Job class to calculate the weather forecast of {@link Constants#DAYS_BATCH} days.
 */
@Component
@Slf4j
public class ForecastJob implements Job {


    @Autowired
    private WeatherForecastRepository repository;

    @Autowired
    private Galaxy galaxy;

    @Override
    public void execute(final JobExecutionContext context) throws JobExecutionException {

        val dataMap = context.getJobDetail().getJobDataMap();
        val forecastList = new HashSet<WeatherForecast>();
        val firstDay = dataMap.getIntegerFromString("firstDay");
        Integer day;

        for (day = firstDay; day < firstDay + DAYS_BATCH; day++) {
            final var weatherForecast = forecast(day, galaxy);
            forecastList.add(weatherForecast);
        }

        repository.saveAll(forecastList);

        if (day == Constants.DAYS_EACH_YEAR * Constants.YEARS) {
            log.info("Last Day calculated");
        }
    }


}
