package spaceinvaders;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import geometricprimitives.Point;
import geometricprimitives.Rectangle;
import io.ParsingUtilities;
import main.Collidable;
import main.HitListener;
import main.HitNotifier;
import main.Sprite;
import main.Velocity;

/**
 * The Class Alien.
 * @author Eden Yefet
 */
public class Alien implements Sprite, Collidable, HitNotifier {
    private List<HitListener> hitListeners;
    private Rectangle rect;
    private List<Image> image;

    private double currentSpeed;
    private int row;
    private int column;
    private int imageIndex;
    private long time;
    private Image shotImg;

    /**
     * Instantiates a new alien.
     * @param x the upperLeft x Cord
     * @param y the upperLeft y Cord
     * @param image the image
     * @param speed the speed
     * @param row the row
     * @param column the column
     */
    Alien(int x, int y, List<Image> image, double speed, int row, int column) {
        this.hitListeners = new ArrayList<>();
        this.rect = new Rectangle(new Point(x, y), 40, 30);
        this.image = image;
        this.currentSpeed = speed;
        this.row = row;
        this.column = column;
        this.imageIndex = 0;
        this.time = System.currentTimeMillis();
        this.shotImg = ParsingUtilities.getImage("block_images/dollar.png");
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Adds the to game.
     * @param g the g
     */
    void addToGame(GameLevel g) {
        g.addCollidable(this);
        // g.addSprite(this);
    }

    /**
     * Change direction.
     */
    void changeDirection() {
        this.currentSpeed *= -1.1;
        double currentX = this.rect.getUpperLeft().getX();
        double currentY = this.rect.getUpperLeft().getY();
        this.rect.setUpperLeft(new Point(currentX, currentY + 40));
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                this.image.get(this.imageIndex));
        if (System.currentTimeMillis() - this.time > 150) {
            this.switchImage();
            this.time = System.currentTimeMillis();
        }

    }

    /**
     * Gets the center.
     * @return the center
     */
    Point getCenter() {
        return this.rect.getCenter();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Gets the column.
     * @return the column
     */
    int getColumn() {
        return this.column;
    }


    /**
     * Gets the position.
     * @return the position
     */
    Point getPosition() {
        return this.rect.getUpperLeft();
    }

    /**
     * Gets the row.
     * @return the row
     */
    int getRow() {
        return this.row;
    }

    @Override
    public void hit(Ball hitter) {
        this.notifyHit(hitter);
    }

    /**
     * Notifies the listeners that this block has been hit.
     * @param hitter The Ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Checks if the alien has reached the screen's limits.
     * @param leftBorder the left border
     * @param rightBorder the right border
     * @return true, if successful
     */
    boolean reachedLimits(int leftBorder, int rightBorder) {
        int leftMostX = (int) this.rect.getUpperLeft().getX();
        int rightMostX = (int) this.rect.getUpperLeft().getX() + (int) this.rect.getWidth();
        return leftMostX < leftBorder || rightMostX > rightBorder;
    }

    /**
     * Removes the alien from the game.
     * @param g GameLevel
     */
    void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Sets the position.
     * @param x the x
     * @param y the y
     */
    void setPosition(int x, int y) {
        Rectangle r = this.rect;
        this.rect = new Rectangle(new Point(x, y), r.getWidth(), r.getHeight());
    }

    /**
     * Sets the speed.
     * @param speed the new speed
     */
    void setSpeed(double speed) {
        this.currentSpeed = speed;
    }

    /**
     * Shoots upwards.
     * @param g the g
     */
    void shoot(GameLevel g) {
        Ball alienShot = new Ball((int) this.getCenter().getX(),
                (int) this.rect.getUpperLeft().getY() + (int) this.rect.getHeight() + 10, 4, Color.RED,
                Velocity.fromAngleAndSpeed(180, 300), false, this.shotImg);
        alienShot.addToGame(g);
        alienShot.setGameEnv(g.getEnvironment());

    }

    /**
     * Switches the image to the next one on the list.
     */
    private void switchImage() {
        this.imageIndex = (this.imageIndex + 1) % this.image.size();
    }

    @Override
    public void timePassed(double dt) {
        double currentX = this.rect.getUpperLeft().getX();
        double currentY = this.rect.getUpperLeft().getY();
        this.rect = new Rectangle(new Point(currentX + this.currentSpeed * dt, currentY), this.rect.getWidth(),
                this.rect.getHeight());
    }

}
