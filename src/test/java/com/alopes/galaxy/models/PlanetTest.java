package com.alopes.galaxy.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import lombok.val;

class PlanetTest {

    @Test
    void should_Not_Move_Day_0() {
        val planet = new Planet(1000.0, 5.0);

        val newPos = planet.getPositionAtDay(0);

        assertEquals(0.0, newPos.getX());
        assertEquals(1000.0, newPos.getY());
    }

    @Test
    void should_Be_At_Initial_Position_After_A_Year() {
        val planet = new Planet(1000.0, 5.0);

        val newPos = planet.getPositionAtDay(360 / 5);

        assertEquals(0.0, newPos.getX());
        assertEquals(1000.0, newPos.getY());
    }

    @Test
    void should_Be_At_X_Axis_After_90_Degress() {
        val planet = new Planet(1000.0, 5.0);

        val newPos = planet.getPositionAtDay(90 / 5);
        assertEquals(1000.0, newPos.getX());
        assertEquals(0.0, newPos.getY());
    }

}
