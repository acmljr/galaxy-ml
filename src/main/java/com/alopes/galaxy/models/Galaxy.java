package com.alopes.galaxy.models;

import java.awt.geom.Point2D;
import lombok.Data;

@Data
public class Galaxy {

    private final Planet ferengi;
    private final Planet betasoide;
    private final Planet vulcano;

    private final Point2D.Double sun = new Point2D.Double(0, 0);


}
