package spaceinvaders;

import java.awt.Image;

import biuoop.DrawSurface;
import geometricprimitives.Line;
import geometricprimitives.Point;
import main.CollisionInfo;
import main.Sprite;
import main.Velocity;

/**
 * This class is responsible for balls.
 * @author Eden Yefet
 */
public class Ball implements Sprite {

    private int size;
    private java.awt.Color color;
    private Point center;
    private Velocity velocity;
    private Line trajectory;
    private GameEnvironment gameEnv;
    private boolean isFriendly;
    private Image img;

    /**
     * Constructor.
     * @param x The x coordinate of the center of the ball
     * @param y The y coordinate of the center of the ball
     * @param r The radius of the ball
     * @param color The color of the ball
     * @param v The velocity of the ball
     * @param isFriendly the is friendly
     * @param img the img
     */
    public Ball(int x, int y, int r, java.awt.Color color, Velocity v, boolean isFriendly, Image img) {
        this.center = new Point(x, y);
        this.size = r;
        this.color = color;
        this.velocity = v;
        this.setFriendly(isFriendly);
        this.img = img;
    }

    /**
     * Instantiates a new ball.
     * @param center The center of the ball
     * @param r The radius of the ball
     * @param color The color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.size = r;
        this.color = color;
    }

    /**
     * Adds the ball to the game.
     * @param g Game
     */
    void addToGame(GameLevel g) {
        g.addSprite(this);
        g.getActiveShots().add(this);
    }

    /**
     * Computes the trajectory of the ball.
     * @param dt the dt
     */
    private void computeTrajectory(double dt) {
        double angle = Math.toRadians(this.velocity.getVelocityAngle());
        Point toRadius = new Point(this.center.getX() + this.size * Math.cos(angle),
                this.center.getY() + this.size * Math.sin(angle));
        Point byVelocity = new Point(this.center.getX() + this.velocity.getDx() * dt,
                this.center.getY() + this.velocity.getDy() * dt);

        // Chooses trajectory to be in the length of the ball's radius or the velocity, the greater from the two
        if (this.center.distance(toRadius) > this.center.distance(byVelocity)) {
            this.trajectory = new Line(this.center, toRadius);
        } else {
            this.trajectory = new Line(this.center, byVelocity);
        }
    }

    /**
     * Draw the ball on the given DrawSurface.
     * @param surface The surface.
     */
    @Override
    public void drawOn(DrawSurface surface) {

        // surface.setColor(this.getColor());
        // surface.fillCircle(this.getX(), this.getY(), this.getSize());
        // surface.setColor(Color.BLACK);
        // surface.drawCircle(this.getX(), this.getY(), this.getSize());
        surface.drawImage(this.getX() - 5, this.getY(), this.img);
        // this.trajectory.drawOn(surface); // Draws the trajectory for testing
    }


    /**
     * Gets the color.
     * @return The color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }


    /**
     * Gets the x.
     * @return The x coordinate of the center
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets the y.
     * @return The y coordinate of the center
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Checks if is friendly.
     * @return the isFriendly
     */
    boolean isFriendly() {
        return this.isFriendly;
    }

    /**
     * Moves the ball to the next step.
     * @param dt the dt
     */
    private void moveOneStep(double dt) {
        this.computeTrajectory(dt);

        CollisionInfo cInfo = this.gameEnv.getClosestCollision(this.trajectory);

        if (cInfo != null) {
            cInfo.collisionObject().hit(this);
        } else {
            this.center =
                    new Velocity(this.velocity.getDx() * dt, this.velocity.getDy() * dt).applyToPoint(this.center);
        }
    }

    /**
     * Removes the ball from the game.
     * @param g Game
     */
    void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.getActiveShots().remove(this);
    }

    /**
     * Sets the friendly status. isFriendly is true if the shot was shot by the SpaceShip and false otherwise.
     * @param friendly the isFriendly to set
     */
    private void setFriendly(boolean friendly) {
        this.isFriendly = friendly;
    }

    /**
     * Sets the game environment.
     * @param gameE The GameEnvironment to be set for the ball.
     */
    void setGameEnv(GameEnvironment gameE) {
        this.gameEnv = gameE;
    }

    @Override
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }
}
