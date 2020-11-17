package com.alopes.galaxy.models.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * Entity responsible for storing the weather forecast in the database.
 */
@Entity
@Table
@Data
public class WeatherForecast {

    @Id
    private Integer day;

    private WeatherCondition weatherCondition;

    private Double trianglePerimeter;

}
