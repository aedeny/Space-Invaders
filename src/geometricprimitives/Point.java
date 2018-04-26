package geometricprimitives;

/**
 * This class is responsible for points.
 * @author Eden Yefet
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructs a point using x and y coordinates.
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other A point to measure the distance to
     * @return The distance of this point to the other point.
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * @param other A point to check if equal.
     * @return True if the points are equal. False otherwise.
     */
    public boolean equals(Point other) {
        return ((this.x == other.getX()) && (this.y == other.getY()));
    }

    /**
     * @return The x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return The y coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * @param xValue The new x-value of the point.
     */
    public void setX(double xValue) {
        this.x = xValue;
    }

    /**
     * @param yValue The new y-value of the point.
     */
    public void setY(double yValue) {
        this.y = yValue;
    }

    /**
     * @param other Point
     * @return The horizontal distance between the points.
     */
    public double xDistance(Point other) {
        return Math.abs(this.x - other.getX());
    }

    /**
     * @param other Point
     * @return The vertical distance between the points.
     */
    public double yDistance(Point other) {
        return Math.abs(this.y - other.getY());
    }

}
