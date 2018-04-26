package geometricprimitives;

import java.awt.Color;
import java.util.List;

import biuoop.DrawSurface;
import main.Sprite;

/**
 * This class is responsible for lines.
 * @author Eden Yefet
 */
public class Line implements Sprite {
    private Point start, end;
    private double a, b, c;

    /**
     * Constructs a line.
     * @param x1 The x coordinate of the start point.
     * @param y1 The y coordinate of the start point.
     * @param x2 The x coordinate of the end point.
     * @param y2 The y coordinate of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {

        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);

        this.a = this.end.getY() - this.start.getY();
        this.b = this.start.getX() - this.end.getX();
        this.c = this.a * this.start.getX() + this.b * this.start.getY();
    }

    /**
     * Constructs a line.
     * @param start Start point.
     * @param end End point.
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());

        this.a = this.end.getY() - this.start.getY();
        this.b = this.start.getX() - this.end.getX();
        this.c = this.a * this.start.getX() + this.b * this.start.getY();
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     * @param rect A Rectangle to check intersection with.
     * @return The closest intersection point, or null if there is no
     *         intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        List<Point> intersectionPoints = rect.intersectionPoints(this);

        if (intersectionPoints.isEmpty()) {

            return null;
        } else {
            Point closestPoint = intersectionPoints.get(0);
            for (int i = 0; i < intersectionPoints.size(); i++) {
                if (closestPoint.distance(this.start()) > intersectionPoints.get(i).distance(this.start())) {
                    closestPoint = intersectionPoints.get(i);
                }

            }

            return closestPoint;
        }
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawLine((int) this.start.getX(), (int) this.start.getY(), (int) this.end.getX(),
                (int) this.end.getY());
    }

    /**
     * @return The end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * @param other Other line
     * @return True if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start());
    }

    /**
     * Using determinant to find intersection.
     * @param other Other line
     * @return The intersection point if the lines intersect, and null
     *         otherwise.
     */
    public Point intersectionWith(Line other) {
        double a1 = this.a;
        double b1 = this.b;
        double c1 = this.c;

        double a2 = other.a;
        double b2 = other.b;
        double c2 = other.c;

        double det = a1 * b2 - a2 * b1;
        if (det == 0) {

            return this.isTouchingOneEdge(other);

        } else {
            Point itrPnt = new Point((b2 * c1 - b1 * c2) / det, (a1 * c2 - a2 * c1) / det);

            // Checks if potential point is within the interval
            if (this.isContainingPoint(itrPnt) && other.isContainingPoint(itrPnt)) {
                return itrPnt;
            } else {
                return null;
            }
        }
    }

    /**
     * @param point The potential intersection point
     * @return True if the point is within the interval and false otherwise
     */
    public boolean isContainingPoint(Point point) {
        return (Math.abs(this.start.distance(point) + this.end.distance(point) - this.length()) < 0.00000001);

    }

    /**
     * @param other Other line
     * @return Returns true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        return (this.intersectionWith(other) != null);
    }

    /**
     * Checks if two lines intersect at one edge.
     * @param other Other line.
     * @return Intersection point or null if there isn't only one.
     */
    public Point isTouchingOneEdge(Line other) {
        if (this.start.equals(other.end)) {
            return this.start;
        } else if (this.end.equals(other.start)) {
            return this.end;
        } else {
            return null;
        }
    }

    /**
     * @return The length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * @return The middle point of the line.
     */
    public Point middle() {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double y2 = this.end.getY();
        double x2 = this.end.getX();

        return new Point((x1 + x2) / 2, (y1 + y2) / 2);
    }

    /**
     * @return The start point of the line
     */
    public Point start() {
        return this.start;
    }

    @Override
    public void timePassed(double dt) {
    }
}
