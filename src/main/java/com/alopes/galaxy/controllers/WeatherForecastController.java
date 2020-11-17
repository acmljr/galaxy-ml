package com.alopes.galaxy.controllers;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alopes.galaxy.models.Galaxy;
import com.alopes.galaxy.models.dtos.WeatherForecastSummary;
import com.alopes.galaxy.models.entities.WeatherForecast;
import com.alopes.galaxy.services.ForecastSummaryService;
import com.alopes.galaxy.services.WeatherForecaster;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller responsible for handling the Weather Forecast related endpoints.
 */
@RestController
@RequestMapping("/weather-forecast")
@Slf4j
public class WeatherForecastController {

    @Autowired
    private ForecastSummaryService summaryService;

    @Autowired
    private Galaxy galaxy;


    /**
     * Shows a summary of the next 10 years of the weather forecast.
     *
     * @return The amount of rainy, dry and with perfect temperature and pressure days.
     */
    @GetMapping("/summary")
    public ResponseEntity<WeatherForecastSummary> getSummary() {

        log.info("Retrieving weather forecast summary.");
        val weatherForecastSummary = summaryService.getNextTenYearsForecastSummary();
        log.info("Successfully retrieved: {}.", weatherForecastSummary);

        return ResponseEntity.ok(weatherForecastSummary);
    }

    /**
     * Forecasts the weather for a specific day.
     *
     * @param day The day [0-3600)
     * @return The weather conditions.
     */
    @GetMapping("/{day}")
    public ResponseEntity<WeatherForecast> getDailyForecast(
            @PathVariable @NotNull @PositiveOrZero final Integer day) {

        log.info("Retrieving weather forecast for day number {}.", day);
        val weatherForecast = WeatherForecaster.forecast(day, galaxy);
        log.info("Successfully retrieved: {}.", weatherForecast);

        return ResponseEntity.ok(weatherForecast);

    }
}
