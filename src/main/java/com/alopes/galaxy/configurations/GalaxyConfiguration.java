package com.alopes.galaxy.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import com.alopes.galaxy.models.Galaxy;
import com.alopes.galaxy.models.Planet;
import lombok.val;

/**
 * Defines the galaxy according to the admission exam specification.
 */
@Component
public class GalaxyConfiguration {

    @Bean
    public Galaxy getGalaxy() {
        val ferengi = new Planet(500.0, 1.0);
        val betasoide = new Planet(2000.0, 3.0);
        val vulcano = new Planet(1000.0, -5.0);
        return new Galaxy(ferengi, betasoide, vulcano);
    }
}
