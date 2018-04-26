package spaceinvaders;

import java.util.ArrayList;
import java.util.List;

import geometricprimitives.Line;
import geometricprimitives.Point;
import main.Collidable;
import main.CollisionInfo;

/**
 * The GameEnvironment class is responsible for the collidable objects.
 *
 * @author Eden Yefet
 */
class GameEnvironment {

    private List<Collidable> collidablesList = new ArrayList<>();

    /**
     * Adds the given collidable to the GameEnvironment.
     *
     * @param c A collidable object to be added.
     */
    void addCollidable(Collidable c) {
        this.collidablesList.add(c);
    }

    /**
     * Finds the closest collision, in case there is one.
     *
     * @param trajectory The trajectory of the object
     * @return The information about the closest
     * collision (a CollisionInfo object) that is going to occur, or null in case there is no collision.
     */
    CollisionInfo getClosestCollision(Line trajectory) {
        if (this.collidablesList.isEmpty()) {
            return null;
        }
        List<Collidable> collidables = new ArrayList<>(this.collidablesList);
        Collidable closestObject = null;
        Point closestIntersection = null;
        Point currentPoint;

        for (Collidable c : collidables) {
            currentPoint = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());

            if (currentPoint == null) {
                continue;
            }

            if (closestIntersection == null || trajectory.start().distance(currentPoint) <= trajectory.start()
                    .distance(closestIntersection)) {
                closestIntersection = currentPoint;
                closestObject = c;
            }
        }
        if (closestIntersection == null) {
            return null;
        }
        return new CollisionInfo(closestIntersection, closestObject);
    }

    /**
     * Removes the given collidable from the GameEnvironment.
     *
     * @param c A collidable object to be removed.
     */
    void removeCollidable(Collidable c) {
        this.collidablesList.remove(c);
    }

}
