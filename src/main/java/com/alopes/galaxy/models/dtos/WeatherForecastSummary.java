package com.alopes.galaxy.models.dtos;

import lombok.Data;

/**
 * DTO used to answer the three main questions of the exam.
 */
@Data
public class WeatherForecastSummary {

    private final Long droughtDaysCount;
    private final Long rainyDaysCount;
    private final Long perfectPressureAndTemperatureDaysCount;
    private final Double maxTrianglePerimeter;

}
