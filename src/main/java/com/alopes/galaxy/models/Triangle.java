package com.alopes.galaxy.models;

import static utils.MathUtils.calculateTriangleArea;
import static utils.MathUtils.calculateTrianglePerimeter;
import static utils.MathUtils.isInside;
import java.awt.geom.Point2D;
import lombok.Data;

@Data
public class Triangle {

    private final Point2D.Double a;
    private final Point2D.Double b;
    private final Point2D.Double c;

    public boolean contains(final Point2D.Double point) {
        return isInside(a.getX(), a.getY(), b.getX(), b.getY(), c.getX(), c.getY(), point.getX(),
                point.getY());
    }

    public Double getArea() {
        return calculateTriangleArea(a.getX(), a.getY(), b.getX(), b.getY(), c.getX(), c.getY());
    }

    public Double getPerimeter() {
        return calculateTrianglePerimeter(a.getX(), a.getY(), b.getX(), b.getY(), c.getX(),
                c.getY());
    }
}
