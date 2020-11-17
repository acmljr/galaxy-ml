package com.alopes.galaxy.services;

import static com.alopes.galaxy.models.entities.WeatherCondition.DRY;
import static com.alopes.galaxy.models.entities.WeatherCondition.NONE;
import static com.alopes.galaxy.models.entities.WeatherCondition.PERFECT_TEMP_PRES;
import static com.alopes.galaxy.models.entities.WeatherCondition.RAINY;
import static utils.MathUtils.isCollinear;
import static utils.MathUtils.setStandardPrecision;
import com.alopes.galaxy.models.Galaxy;
import com.alopes.galaxy.models.Triangle;
import com.alopes.galaxy.models.entities.WeatherForecast;
import lombok.val;

public class WeatherForecaster {

    public static WeatherForecast forecast(final Integer day, final Galaxy galaxy) {
        val weatherForecast = new WeatherForecast();
        weatherForecast.setWeatherCondition(NONE);
        weatherForecast.setDay(day);

        val ferengiPos = galaxy.getFerengi().getPositionAtDay(day);
        val betasoidePos = galaxy.getBetasoide().getPositionAtDay(day);
        val vulcanoPos = galaxy.getVulcano().getPositionAtDay(day);


        // First of all, check if the planets are aligned:
        val planetsAligned = isCollinear(ferengiPos, betasoidePos, vulcanoPos);

        // Then we check if the sun is aligned with them as well:
        val sunAlignedWithPlanets = isCollinear(ferengiPos, betasoidePos, galaxy.getSun());

        // If the planets are aligned, it's not a rainy day.
        if (planetsAligned) {

            // If the sun is also aligned with the planets, it's a drought.
            if (sunAlignedWithPlanets) {
                weatherForecast.setWeatherCondition(DRY);
            } else { // Otherwise, it's a day with perfect temperature and pressure.
                weatherForecast.setWeatherCondition(PERFECT_TEMP_PRES);
            }

            return weatherForecast;
        }

        // If the planets aren't aligned, we must check if the sun lies inside the triangle formed
        // by the planets.

        val triangle = new Triangle(ferengiPos, betasoidePos, vulcanoPos);
        if (triangle.contains(galaxy.getSun())) {
            weatherForecast.setWeatherCondition(RAINY);
            weatherForecast.setTrianglePerimeter(setStandardPrecision(triangle.getPerimeter()));
        }

        return weatherForecast;
    }

}
