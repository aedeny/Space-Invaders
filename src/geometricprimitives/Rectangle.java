package geometricprimitives;

import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle class.
 * @author Eden Yefet
 */
public class Rectangle {

    private Point upperLeft;
    private Point center;
    private Line up, down, right, left;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft The upper left point of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;

        this.up = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY());
        this.down = new Line(upperLeft.getX(), upperLeft.getY() + height, upperLeft.getX() + width,
                upperLeft.getY() + height);
        this.right = new Line(upperLeft.getX() + width, upperLeft.getY(), upperLeft.getX() + width,
                upperLeft.getY() + height - 1);
        this.left = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + height);
        this.center = new Point(upperLeft.getX() + width * 0.5, upperLeft.getY() + height * 0.85);
    }

    /**
     * @return The center point of the rectangle.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * @return The lower line.
     */
    public Line getDown() {
        return this.down;
    }

    /**
     * @return The height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return The left line.
     */
    public Line getLeft() {
        return this.left;
    }

    /**
     * @return The right line.
     */
    public Line getRight() {
        return this.right;
    }

    /**
     * @return The upper line.
     */
    public Line getUp() {
        return this.up;
    }

    /**
     * @return The upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    // Return
    /**
     * @return The width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns a possibly empty List of intersection points with the specified line.
     * @param line A line to check intersections with.
     * @return A list of intersection points with the triangle.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<Point>();

        if (line.intersectionWith(this.up) != null) {
            intersectionPoints.add(line.intersectionWith(this.up));
        }
        if (line.intersectionWith(this.down) != null) {
            intersectionPoints.add(line.intersectionWith(this.down));
        }
        if (line.intersectionWith(this.right) != null) {
            intersectionPoints.add(line.intersectionWith(this.right));
        }
        if (line.intersectionWith(this.left) != null) {
            intersectionPoints.add(line.intersectionWith(this.left));
        }
        return intersectionPoints;
    }

    /**
     * @param upLeft Point to be the upper left point of the rectangle.
     */
    public void setUpperLeft(Point upLeft) {
        this.upperLeft = upLeft;
    }
}
