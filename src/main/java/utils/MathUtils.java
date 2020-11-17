package utils;

import static java.awt.geom.Point2D.distance;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.val;

public class MathUtils {

    public static final Double PRECISION = 1e-6;

    /**
     * Checks if three 2D points are collinear.
     *
     * @param x1 X coordinate of the first point.
     * @param y1 Y coordinate of the first point.
     * @param x2 X coordinate of the second point.
     * @param y2 Y coordinate of the second point.
     * @param x3 X coordinate of the third point.
     * @param y3 Y coordinate of the third point.
     * @return true if the points are collinear.
     */
    public static Boolean isCollinear(final Double x1, final Double y1, final Double x2,
            final Double y2, final Double x3, final Double y3) {
        val leftSide = (y1 - y2) * (x1 - x3);
        val rightSide = (y1 - y3) * (x1 - x2);

        return leftSide - rightSide < PRECISION;
    }

    /**
     * Checks if three 2D points are collinear.
     *
     * @param point1 First point.
     * @param point2 Second point.
     * @param point3 Third point.
     * @return true if the points are collinear.
     */
    public static Boolean isCollinear(final Point2D.Double point1, final Point2D.Double point2,
            final Point2D.Double point3) {

        return isCollinear(point1.getX(), point1.getY(), point2.getX(), point2.getY(),
                point3.getX(), point3.getY());
    }

    /*
     * A utility function to calculate area of triangle formed by (x1, y1) (x2, y2) and (x3, y3)
     */
    public static Double calculateTriangleArea(final Double x1, final Double y1, final Double x2,
            final Double y2, final Double x3, final Double y3) {
        return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
    }

    /**
     * A function to check whether point P(x, y) lies inside the triangle formed by A(x1, y1), B(x2,
     * y2) and C(x3, y3). It considers {@link MathUtils#PRECISION}.
     *
     * @param x1 X coordinate of the first triangle corner.
     * @param y1 Y coordinate of the first triangle corner.
     * @param x2 X coordinate of the second triangle corner.
     * @param y2 Y coordinate of the second triangle corner.
     * @param x3 X coordinate of the third triangle corner.
     * @param y3 Y coordinate of the third triangle corner.
     * @param x X coordinate of the first point
     * @param y X coordinate of the first point
     * @return True if the point is inside the triangle.
     */
    public static boolean isInside(final Double x1, final Double y1, final Double x2,
            final Double y2, final Double x3, final Double y3, final Double x, final Double y) {
        /* Calculate area of triangle ABC */
        final double A = calculateTriangleArea(x1, y1, x2, y2, x3, y3);

        /* Calculate area of triangle PBC */
        final double A1 = calculateTriangleArea(x, y, x2, y2, x3, y3);

        /* Calculate area of triangle PAC */
        final double A2 = calculateTriangleArea(x1, y1, x, y, x3, y3);

        /* Calculate area of triangle PAB */
        final double A3 = calculateTriangleArea(x1, y1, x2, y2, x, y);

        /* Check if sum of A1, A2 and A3 is same as A */
        return A - A1 - A2 - A3 < PRECISION;
    }

    /**
     * A function to check whether point P(x, y) lies inside the triangle formed by A(x1, y1), B(x2,
     * y2) and C(x3, y3). It considers {@link MathUtils#PRECISION}.
     *
     * @param x1 X coordinate of the first triangle corner (A).
     * @param y1 Y coordinate of the first triangle corner (A).
     * @param x2 X coordinate of the second triangle corner (B).
     * @param y2 Y coordinate of the second triangle corner (B).
     * @param x3 X coordinate of the third triangle corner (C).
     * @param y3 Y coordinate of the third triangle corner (C).
     * @return The triangle Perimeter.
     */
    public static Double calculateTrianglePerimeter(final Double x1, final Double y1,
            final Double x2, final Double y2, final Double x3, final Double y3) {

        val AB = distance(x1, y1, x2, y2);
        val AC = distance(x1, y1, x3, y3);
        val BC = distance(x2, y2, x3, y3);

        return AB + AC + BC;
    }

    public static Double setStandardPrecision(final Double x) {
        return BigDecimal.valueOf(x).setScale(6, RoundingMode.HALF_UP).doubleValue();
    }


}
