package spaceinvaders;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometricprimitives.Point;
import geometricprimitives.Rectangle;
import io.ParsingUtilities;
import main.Collidable;
import main.HitListener;
import main.HitNotifier;
import main.Sprite;
import main.Velocity;

/**
 * This class is responsible for the game paddle.
 * @author Eden Yefet
 */
public class SpaceShip implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private biuoop.KeyboardSensor keyboard;
    private int speed;
    private long lastTimeShot;
    private GameLevel game;
    private List<HitListener> hitListeners;
    private Image shotImg;
    private Image shipImage;

    /**
     * Constructor.
     * @param rect The rectangle of the paddle
     * @param keyboard KeyboardSensor
     * @param paddleSpeed The speed of the paddle
     * @param game the game
     */
    SpaceShip(Rectangle rect, biuoop.KeyboardSensor keyboard, int paddleSpeed, GameLevel game) {
        this.hitListeners = new ArrayList<>();
        this.rect = rect;
        this.keyboard = keyboard;
        this.speed = paddleSpeed;
        this.game = game;
        this.shotImg = ParsingUtilities.getImage("block_images/shot.png");
        this.lastTimeShot = System.currentTimeMillis();
        this.shipImage = ParsingUtilities.getImage("block_images/spaceShip.png");
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Adds the paddle to the game.
     * @param g The game.
     */
    void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(), this.shipImage);

    }

    /**
     * Gets the center.
     * @return the center
     */
    private Point getCenter() {
        return this.rect.getCenter();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public void hit(Ball hitter) {
        this.notifyHit(hitter);
    }

    /**
     * Moves the paddle left.
     * @param dt the dt
     */
    private void moveLeft(double dt) {
        if (this.rect.getUpperLeft().getX() - GameLevel.BORDERS_SIZE > this.speed * dt) {
            this.rect = new Rectangle(
                    new Point(this.rect.getUpperLeft().getX() - this.speed * dt, this.rect.getUpperLeft().getY()),
                    this.rect.getWidth(), this.rect.getHeight());
        } else {
            this.rect = new Rectangle(new Point(GameLevel.BORDERS_SIZE, this.rect.getUpperLeft().getY()),
                    this.rect.getWidth(), this.rect.getHeight());
        }
    }

    /**
     * Moves the paddle right.
     * @param dt the dt
     */
    private void moveRight(double dt) {
        if (this.rect.getUpperLeft().getX() + this.rect.getWidth() + GameLevel.BORDERS_SIZE < 800) {
            this.rect = new Rectangle(
                    new Point(this.rect.getUpperLeft().getX() + this.speed * dt, this.rect.getUpperLeft().getY()),
                    this.rect.getWidth(), this.rect.getHeight());
        } else {
            this.rect = new Rectangle(
                    new Point(800 - GameLevel.BORDERS_SIZE - this.rect.getWidth(), this.rect.getUpperLeft().getY()),
                    this.rect.getWidth(), this.rect.getHeight());
        }
    }

    /**
     * Notifies the listeners that this block has been hit.
     * @param hitter The Ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Removes the paddle from the game.
     * @param g GameLevel
     */
    void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Shoot.
     */
    void shoot() {
        Ball shot = new Ball((int) this.getCenter().getX() + 4, (int) this.rect.getUpperLeft().getY() - 10, 3,
                Color.YELLOW, Velocity.fromAngleAndSpeed(0, 600), true, this.shotImg);
        shot.addToGame(this.game);
        shot.setGameEnv(this.game.getEnvironment());

    }

    @Override
    public void timePassed(double dt) {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)
                && System.currentTimeMillis() - this.lastTimeShot >= 350) {
            this.shoot();
            this.lastTimeShot = System.currentTimeMillis();
        }

    }
}
