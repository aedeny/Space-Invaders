package main;

import geometricprimitives.Point;

/**
 * CollisionInfo class.
 * @author Eden Yefet
 */
public class CollisionInfo {
    private Point colPoint;
    private Collidable colObject;

    /**
     * Constructor.
     * @param cPoint The expected collision point.
     * @param cObject The expected collision object.
     */
    public CollisionInfo(Point cPoint, Collidable cObject) {
        this.colPoint = cPoint;
        this.colObject = cObject;
    }

    /**
     * Collision object.
     * @return The collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.colObject;
    }

    /**
     * Collision point.
     * @return The point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.colPoint;
    }
}
