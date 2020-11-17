package com.alopes.galaxy.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.alopes.galaxy.models.Galaxy;
import com.alopes.galaxy.models.Planet;
import com.alopes.galaxy.models.entities.WeatherCondition;
import lombok.val;

class WeatherForecasterTest {

    private final Galaxy galaxy =
            new Galaxy(new Planet(500.0, 1.0), new Planet(2000.0, 3.0), new Planet(1000.0, -5.0));

    @Test
    void should_Be_Dry_Day_0() {
        val weatherForecast = WeatherForecaster.forecast(0, galaxy);

        assertEquals(WeatherCondition.DRY, weatherForecast.getWeatherCondition());
    }

    @Test
    void should_Be_Rainy_Day_20() {
        val weatherForecast = WeatherForecaster.forecast(20, galaxy);

        assertEquals(WeatherCondition.RAINY, weatherForecast.getWeatherCondition());
    }

}
