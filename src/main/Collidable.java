package main;

import geometricprimitives.Rectangle;
import spaceinvaders.Ball;

/**
 * Collidable class.
 * @author Eden Yefet
 */
public interface Collidable {

    /**
     * Gets the collision rectangle.
     * @return The "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param hitter The hitting ball
     */
    void hit(Ball hitter);
}
