package com.alopes.galaxy.models;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;
import static utils.MathUtils.setStandardPrecision;
import java.awt.geom.Point2D;
import lombok.Data;
import lombok.val;

@Data
public class Planet {

    private final Double sunDistance;
    private final Double angularSpeed; // degree/day

    public Point2D.Double getPositionAtDay(final Integer day) {

        var newAngle = Math.abs(angularSpeed) * day % 360;

        if (angularSpeed < 0) { // counterclockwise
            newAngle = 360 - newAngle;
        }

        val newAngleInRadians = toRadians(newAngle);

        val newX = sunDistance * sin(newAngleInRadians);
        val newY = sunDistance * cos(newAngleInRadians);


        return new Point2D.Double(setStandardPrecision(newX), setStandardPrecision(newY));
    }

}
