package main;

import geometricprimitives.Point;

/**
 * Velocity specifies the change in position on the X and the Y axes.
 * @author Eden Yefet
 */
public class Velocity {
    /**
     * Construct velocity from angle and speed.
     * @param angle The angle of the velocity
     * @param speed The speed to move
     * @return Velocity in terms of dx and dy.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin((180 - angle) * Math.PI / 180);
        double dy = speed * Math.cos((180 - angle) * Math.PI / 180);
        return new Velocity(dx, dy);
    }

    private double dx, dy;

    private double xAcceleration, yAcceleration;

    /**
     * Constructor.
     * @param dx The change on the x axis
     * @param dy The change on the y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructor.
     * @param dx The change on the x axis
     * @param dy The change on the y axis
     * @param xAcc x acceleration
     * @param yAcc y acceleration
     */
    public Velocity(double dx, double dy, double xAcc, double yAcc) {
        this.dx = dx;
        this.dy = dy;
        this.xAcceleration = xAcc;
        this.yAcceleration = yAcc;
    }

    /**
     * Applies acceleration.
     */
    public void applyAcceleration() {
        this.dx += this.xAcceleration;
        this.dy += this.yAcceleration;
    }

    /**
     * Apply to point.
     * @param p A point with position (x,y)
     * @return A new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Gets the dx.
     * @return dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets the dy.
     * @return dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Gets the speed.
     * @return The velocity speed / force.
     */
    public double getSpeed() {
        return Math.sqrt(this.getDx() * this.getDx() + this.getDy() * this.getDy());
    }

    /**
     * Gets the velocity angle.
     * @return The velocity angle.
     */
    public double getVelocityAngle() {
        return (360 + Math.toDegrees(Math.atan2(this.dy, this.dx))) % 360;
    }

    /**
     * Sets acceleration.
     * @param xAcc x acceleration
     * @param yAcc y acceleration
     */
    public void setAcceleration(double xAcc, double yAcc) {
        this.xAcceleration = xAcc;
        this.yAcceleration = yAcc;
    }

    /**
     * Sets the dx.
     * @param dX The change on the x axis
     */
    public void setDx(double dX) {
        this.dx = dX;
    }

    /**
     * Sets the dy.
     * @param dY The change on the y axis
     */
    public void setDy(double dY) {
        this.dy = dY;
    }
}
