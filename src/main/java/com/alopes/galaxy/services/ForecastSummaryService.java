package com.alopes.galaxy.services;

import static com.alopes.galaxy.models.entities.WeatherCondition.DRY;
import static com.alopes.galaxy.models.entities.WeatherCondition.PERFECT_TEMP_PRES;
import static com.alopes.galaxy.models.entities.WeatherCondition.RAINY;
import static java.util.Comparator.comparingDouble;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alopes.galaxy.models.dtos.WeatherForecastSummary;
import com.alopes.galaxy.models.entities.WeatherForecast;
import com.alopes.galaxy.repositories.WeatherForecastRepository;
import lombok.val;

@Service
public class ForecastSummaryService {

    @Autowired
    private WeatherForecastRepository repository;

    public WeatherForecastSummary getNextTenYearsForecastSummary() {

        val tenYearForecast = repository.findAll();

        val orderedForecast =
                tenYearForecast.stream().filter(wf -> wf.getTrianglePerimeter() != null)
                        .sorted(comparingDouble(WeatherForecast::getTrianglePerimeter).reversed())
                        .collect(Collectors.toList());

        val maxPerimeter = orderedForecast.get(0).getTrianglePerimeter();

        val numberOfRainyDays = tenYearForecast.stream()
                .filter(wf -> wf.getWeatherCondition().equals(RAINY)).count();

        val numberOfDryDays =
                tenYearForecast.stream().filter(wf -> wf.getWeatherCondition().equals(DRY)).count();

        val numberOfPerfect = tenYearForecast.stream()
                .filter(wf -> wf.getWeatherCondition().equals(PERFECT_TEMP_PRES)).count();


        return new WeatherForecastSummary(numberOfDryDays, numberOfRainyDays, numberOfPerfect,
                maxPerimeter);
    }

}
