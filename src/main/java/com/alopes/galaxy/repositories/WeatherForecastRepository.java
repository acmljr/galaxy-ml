package com.alopes.galaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alopes.galaxy.models.entities.WeatherForecast;

@Repository
public interface WeatherForecastRepository extends JpaRepository<WeatherForecast, Integer> {

}
